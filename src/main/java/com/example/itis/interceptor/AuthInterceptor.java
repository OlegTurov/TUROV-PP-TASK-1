package com.example.itis.interceptor;

import com.example.itis.entity.UserEntity;
import com.example.itis.repository.SessionRepository;
import com.example.itis.service.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final SessionService sessionService;

    @SneakyThrows
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        String token = getSessionToken(request);

        if (token == null) {
            response.sendRedirect("/login");
            return false;
        }

        UserEntity user =
                sessionService.findBySession(token);

        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }

        request.setAttribute("user", user);

        return true;
    }

    private String getSessionToken(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if ("SESSION_ID".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }
}