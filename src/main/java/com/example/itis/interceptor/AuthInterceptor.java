package com.example.itis.interceptor;

import com.example.itis.entity.UserEntity;
import com.example.itis.repository.SessionRepository;
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

    private final SessionRepository sessionRepository;

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

        Optional<UserEntity> userId =
                sessionRepository.findBySession(token);

        if (userId.isEmpty()) {
            response.sendRedirect("/login");
            return false;
        }

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