package com.example.itis.repository;

import com.example.itis.entity.UserEntity;
import com.example.itis.entity.UserSessionEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SessionRepository {
    void saveSession(UserSessionEntity userSessionEntity);

    Optional<UserEntity> findBySession(String token);
}
