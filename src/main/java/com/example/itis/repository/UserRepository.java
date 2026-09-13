package com.example.itis.repository;

import com.example.itis.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    void save(UserEntity user);
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
