package com.example.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class DatabaseHealthService {

    private final DataSource dataSource;

    @Autowired
    public DatabaseHealthService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, Object> checkDatabaseHealth() {
        Map<String, Object> healthInfo = new HashMap<>();
        
        try (Connection connection = dataSource.getConnection()) {
            // Test basic connectivity
            boolean isValid = connection.isValid(5); // 5 second timeout
            
            if (isValid) {
                healthInfo.put("status", "UP");
                healthInfo.put("database", connection.getMetaData().getDatabaseProductName());
                healthInfo.put("url", connection.getMetaData().getURL());
                healthInfo.put("driver", connection.getMetaData().getDriverName());
                healthInfo.put("version", connection.getMetaData().getDatabaseProductVersion());
            } else {
                healthInfo.put("status", "DOWN");
                healthInfo.put("error", "Database connection validation failed");
            }
            
        } catch (SQLException e) {
            healthInfo.put("status", "DOWN");
            healthInfo.put("error", e.getMessage());
            healthInfo.put("errorCode", e.getErrorCode());
        }
        
        return healthInfo;
    }
}
