package com.example.itis.service;

import com.example.itis.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void send(PostResponse post) {

        messagingTemplate.convertAndSend("/topic/posts", post);
    }
}