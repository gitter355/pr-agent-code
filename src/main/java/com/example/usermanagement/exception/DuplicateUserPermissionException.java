package com.example.usermanagement.exception;

public class DuplicateUserPermissionException extends RuntimeException {
    public DuplicateUserPermissionException(String message) {
        super(message);
    }
}
