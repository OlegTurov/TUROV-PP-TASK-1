package com.example.itis.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

public interface PostsController {
    String posts(Model model);
    String createPost(String text, HttpServletRequest request);
    String logout(HttpServletRequest request, HttpServletResponse response);
}
