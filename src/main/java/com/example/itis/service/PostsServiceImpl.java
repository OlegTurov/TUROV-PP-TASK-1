package com.example.itis.service;

import com.example.itis.dto.PostResponse;
import com.example.itis.entity.PostEntity;
import com.example.itis.entity.UserEntity;
import com.example.itis.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final PostsWebSocketService postWebSocketService;
    private final static Logger log = LoggerFactory.getLogger(PostsServiceImpl.class);

    @Override
    public void create(UserEntity userEntity, String text) {
        PostEntity post = PostEntity.builder()
                .text(text)
                .userId(userEntity.getId())
                .build();

        postsRepository.save(post);

        log.info("Post created: {} {}", userEntity.getEmail(), post.getText());

        PostResponse response = new PostResponse(
                userEntity.getEmail(),
                post.getText(),
                post.getCreatedAt()
        );

        postWebSocketService.send(response);

        log.info("Post was send through WebSocket: {} {}", post.getEmail(), post.getText());
    }

    @Override
    public List<PostResponse> findAll() {
        return postsRepository.findLatest()
                .stream()
                .map(post -> new PostResponse(
                        post.getEmail(),
                        post.getText(),
                        post.getCreatedAt()
                ))
                .toList();
    }
}
