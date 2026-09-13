package com.example.itis.service;

import com.example.itis.entity.UserEntity;

import java.util.Optional;

public interface SessionService {
    String createSession(Long userId);
    Optional<UserEntity> findBySession(String token);
}
