package com.example.itis.controller;

import com.example.itis.dto.RegisterRequest;
import com.example.itis.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthControllerImpl.class);
    private final AuthService authService;

    @Override
    @GetMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String registerPage() {
        return "register";
    }

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(RegisterRequest registerRequest) {
        try {
            String email = registerRequest.email();
            String password = registerRequest.password();
            authService.register(email, password);
            log.info("User with email {} was registered", email);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "register";
    }
}