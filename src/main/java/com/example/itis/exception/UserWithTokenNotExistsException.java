package com.example.itis.exception;

public class UserWithTokenNotExistsException extends RuntimeException {
    public UserWithTokenNotExistsException(String token) {
        super("User with token %s not exists".formatted(token));
    }
}
