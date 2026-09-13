package com.example.itis.repository;

import com.example.itis.entity.UserEntity;
import com.example.itis.entity.UserSessionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public Optional<UserEntity> findBySession(String token) {
        String sql = """
            SELECT id, email, password
            FROM users INNER JOIN user_sessions ON users.id = user_sessions.user_id
            WHERE token = ?
            """;
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> UserEntity.builder()
                        .id(rs.getLong("id"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .build(),
                token
        ).stream().findFirst();
    }
}