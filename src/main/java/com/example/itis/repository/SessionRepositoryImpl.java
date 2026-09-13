package com.example.itis.repository;

import com.example.itis.entity.UserSessionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SessionRepositoryImpl implements SessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveSession(UserSessionEntity userSession) {
        String sql = """
                INSERT INTO user_sessions (
                    user_id,
                    token,
                    expires_at
                )
                VALUES (?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                userSession.getUserId(),
                userSession.getToken(),
                userSession.getExpiresAt()
        );
    }
}