package com.example.usermanagement.service;

import com.example.usermanagement.entity.Permission;
import com.example.usermanagement.exception.PermissionNotFoundException;
import com.example.usermanagement.exception.DuplicatePermissionException;
import com.example.usermanagement.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    
    private final PermissionRepository permissionRepository;
    
    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
    
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found with id: " + id));
    }
    
    public Permission createPermission(Permission permission) {
        if (permissionRepository.existsByName(permission.getName())) {
            throw new DuplicatePermissionException("Permission with name " + permission.getName() + " already exists");
        }
        return permissionRepository.save(permission);
    }
    
    public Permission updatePermission(Long id, Permission permissionDetails) {
        Permission permission = getPermissionById(id);
        
        // Check if name is being changed and if it already exists
        if (!permission.getName().equals(permissionDetails.getName()) && 
            permissionRepository.existsByName(permissionDetails.getName())) {
            throw new DuplicatePermissionException("Permission with name " + permissionDetails.getName() + " already exists");
        }
        
        permission.setName(permissionDetails.getName());
        permission.setDescription(permissionDetails.getDescription());
        
        return permissionRepository.save(permission);
    }
    
    public void deletePermission(Long id) {
        Permission permission = getPermissionById(id);
        permissionRepository.delete(permission);
    }
    
    public Optional<Permission> getPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }
} 