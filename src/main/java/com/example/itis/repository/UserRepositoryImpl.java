package com.example.itis.repository;

import com.example.itis.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public boolean existsByEmail(String email) {
        String sql = """
                SELECT EXISTS(
                    SELECT 1
                    FROM users
                    WHERE email = ?
                )
                """;

        return Boolean.TRUE.equals(
                jdbcTemplate.queryForObject(sql, Boolean.class, email)
        );
    }

    public void save(UserEntity userEntity) {
        String sql = """
                INSERT INTO users (email, password)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, userEntity.getEmail(), userEntity.getPassword());
    }
}