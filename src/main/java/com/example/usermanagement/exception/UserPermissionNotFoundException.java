package com.example.usermanagement.exception;

public class UserPermissionNotFoundException extends RuntimeException {
    public UserPermissionNotFoundException(String message) {
        super(message);
    }
}
