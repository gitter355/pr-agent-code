package com.example.usermanagement.controller;

import com.example.usermanagement.service.DatabaseHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HealthController {

    private final DatabaseHealthService databaseHealthService;

    @Autowired
    public HealthController(DatabaseHealthService databaseHealthService) {
        this.databaseHealthService = databaseHealthService;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> healthStatus = new HashMap<>();
        Map<String, Object> databaseHealth = databaseHealthService.checkDatabaseHealth();
        
        // Overall application status
        String overallStatus = "UP".equals(databaseHealth.get("status")) ? "UP" : "DOWN";
        
        healthStatus.put("status", overallStatus);
        healthStatus.put("timestamp", LocalDateTime.now());
        healthStatus.put("service", "user-management");
        healthStatus.put("version", "0.0.1-SNAPSHOT");
        
        // Add database health details
        healthStatus.put("database", databaseHealth);
        
        // Return appropriate HTTP status
        HttpStatus httpStatus = "UP".equals(overallStatus) ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE;
        return ResponseEntity.status(httpStatus).body(healthStatus);
    }
}
