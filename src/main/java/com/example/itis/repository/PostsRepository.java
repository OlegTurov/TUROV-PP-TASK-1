package com.example.itis.repository;

import com.example.itis.entity.PostEntity;

import java.util.List;

public interface PostsRepository {
    void save(PostEntity post);
    List<PostEntity> findLatest();
}
