package com.example.itis.repository;

import com.example.itis.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        String sql = """
            SELECT id, email, password
            FROM users
            WHERE email = ?
            """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> UserEntity.builder()
                        .id(rs.getLong("id"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .build(),
                email
        ).stream().findFirst();
    }

    public void save(UserEntity userEntity) {
        String sql = """
                INSERT INTO users (email, password)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, userEntity.getEmail(), userEntity.getPassword());
    }
}