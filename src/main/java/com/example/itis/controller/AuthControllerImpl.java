package com.example.itis.controller;

import com.example.itis.RegisterRequest;
import com.example.itis.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthControllerImpl.class);
    private final AuthService authService;

    @Override
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @Override
    @PostMapping("/register")
    public String register(RegisterRequest registerRequest) {
        try {
            String email = registerRequest.email();
            String password = registerRequest.password();
            authService.register(email, password);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "register";
    }
}