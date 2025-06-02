package com.example.usermanagement.dto;

import com.example.usermanagement.entity.User;
import jakarta.validation.constraints.NotNull;

public class PermissionUpdateRequest {
    
    @NotNull(message = "Permission is required")
    private User.Permission permission;
    
    public PermissionUpdateRequest() {
    }
    
    public PermissionUpdateRequest(User.Permission permission) {
        this.permission = permission;
    }
    
    public User.Permission getPermission() {
        return permission;
    }
    
    public void setPermission(User.Permission permission) {
        this.permission = permission;
    }
    
    @Override
    public String toString() {
        return "PermissionUpdateRequest{" +
                "permission=" + permission +
                '}';
    }
} 