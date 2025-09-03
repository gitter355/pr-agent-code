package com.example.usermanagement.exception;

public class DuplicatePermissionException extends RuntimeException {
    
    public DuplicatePermissionException(String message) {
        super(message);
    }
    
    public DuplicatePermissionException(String message, Throwable cause) {
        super(message, cause);
    }
} 