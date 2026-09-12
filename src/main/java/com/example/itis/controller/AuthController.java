package com.example.itis.controller;

import com.example.itis.dto.RegisterRequest;

public interface AuthController {
    String registerPage();
    String register(RegisterRequest registerRequest);
}