package com.example.itis.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserSessionEntity {
    private Long id;
    private Long userId;
    private String token;
    private LocalDateTime expiresAt;
}
