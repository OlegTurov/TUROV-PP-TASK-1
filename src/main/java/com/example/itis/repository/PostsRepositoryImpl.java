package com.example.itis.repository;

import com.example.itis.dto.PostResponse;
import com.example.itis.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(PostEntity post) {

        String sql = """
                INSERT INTO posts (
                    user_id,
                    text,
                    created_at
                )
                VALUES (?, ?, CURRENT_TIMESTAMP)
                """;

        jdbcTemplate.update(
                sql,
                post.getUserId(),
                post.getText()
        );
    }

    public List<PostEntity> findLatest() {
        String sql = """
                SELECT
                    p.id,
                    u.email,
                    p.text,
                    p.created_at
                FROM posts p
                JOIN users u ON u.id = p.user_id
                ORDER BY p.created_at DESC, p.id DESC
                LIMIT 10
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> PostEntity.builder()
                        .id(rs.getLong("id"))
                        .email(rs.getString("email"))
                        .text(rs.getString("text"))
                        .createdAt(
                                rs.getTimestamp("created_at")
                                        .toLocalDateTime()
                        )
                        .build()
        );
    }
}
