package com.example.itis.controller;

import com.example.itis.dto.AuthenticateRequest;
import com.example.itis.dto.RegisterRequest;
import com.example.itis.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Duration;

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
    public String register(RegisterRequest registerRequest) {
        String email = registerRequest.email();
        String password = registerRequest.password();
        authService.register(email, password);
        return "redirect:/login";
    }

    @Override
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String loginPage() {
        return "login";
    }

    @Override
    @PostMapping("/login")
    public String login(AuthenticateRequest authenticateRequest, HttpServletResponse response) {
        String token = authService.login(authenticateRequest.email(), authenticateRequest.password());
        ResponseCookie cookie = ResponseCookie
                .from("SESSION_ID", token)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );
        return "redirect:/posts";
    }
}