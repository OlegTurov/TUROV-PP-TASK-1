package com.example.itis.service;

import com.example.itis.dto.PostResponse;
import com.example.itis.entity.UserEntity;

import java.util.List;

public interface PostsService {
    void create(UserEntity userEntity, String text);
    List<PostResponse> findAll();
}
