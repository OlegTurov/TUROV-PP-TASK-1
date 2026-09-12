package com.example.itis.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEntity {
    private long id;
    private String email;
    private String password;
}
