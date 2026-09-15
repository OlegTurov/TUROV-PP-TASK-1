package com.example.itis.exception;

public class UserPasswordIncorrectException extends RuntimeException {
    public UserPasswordIncorrectException(String email) {
        super("Incorrect password for user " + email);
    }
}
