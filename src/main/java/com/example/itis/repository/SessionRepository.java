package com.example.itis.repository;

import com.example.itis.entity.UserSessionEntity;

public interface SessionRepository {
    void saveSession(UserSessionEntity userSessionEntity);
}
