package com.example.itis.controller;

import com.example.itis.entity.UserEntity;
import com.example.itis.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostsControllerImpl implements PostsController {

    private final PostsService postsService;

    @GetMapping("/posts")
    public String posts(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getAttribute("user"));
        model.addAttribute("posts", postsService.findAll());
        return "posts";
    }

    @PostMapping("/posts")
    public String createPost(@RequestParam String text, HttpServletRequest request) {
        UserEntity user =
                (UserEntity) request.getAttribute("user");

        postsService.create(user, text);

        return "redirect:/posts";
    }

    @Override
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");

        ResponseCookie cookie = ResponseCookie
                .from("SESSION_ID", "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );
        return "redirect:/login";
    }
}