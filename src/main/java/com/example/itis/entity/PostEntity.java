package com.example.itis.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostEntity {
    private Long id;
    private Long userId;
    private String email;
    private String text;
    private LocalDateTime createdAt;
}
