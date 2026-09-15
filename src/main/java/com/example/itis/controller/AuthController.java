package com.example.itis.controller;

import com.example.itis.dto.AuthenticateRequest;
import com.example.itis.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthController {
    String registerPage();
    String register(RegisterRequest registerRequest);
    String loginPage();
    String login(AuthenticateRequest authenticateRequest, HttpServletResponse response);
}