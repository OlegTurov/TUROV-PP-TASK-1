package com.example.itis.exception;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String email) {
        super("User with email " + email + " doesnt exist");
    }
}
