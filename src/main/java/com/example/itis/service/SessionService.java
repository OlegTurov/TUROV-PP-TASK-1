package com.example.itis.service;

import com.example.itis.entity.UserEntity;

public interface SessionService {
    String createSession(Long userId);
    UserEntity findBySession(String token);
}
