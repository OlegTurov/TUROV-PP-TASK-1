package com.example.itis.service;

import com.example.itis.entity.UserEntity;
import com.example.itis.entity.UserSessionEntity;
import com.example.itis.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    private final SecureRandom secureRandom = new SecureRandom();

    public String createSession(Long userId) {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);

        String token = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);

        LocalDateTime expiresAt = LocalDateTime.now()
                .plusDays(7);

        UserSessionEntity userSessionEntity = UserSessionEntity.builder()
                .userId(userId)
                .expiresAt(expiresAt)
                .token(token)
                .build();

        sessionRepository.saveSession(userSessionEntity);

        return token;
    }

    @Override
    public Optional<UserEntity> findBySession(String token) {
        return Optional.empty();
    }
}