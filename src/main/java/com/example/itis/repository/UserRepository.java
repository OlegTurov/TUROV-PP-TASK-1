package com.example.itis.repository;

import com.example.itis.entity.UserEntity;

public interface UserRepository {

    void save(UserEntity user);
    boolean existsByEmail(String email);
}
