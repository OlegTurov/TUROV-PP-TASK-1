package com.example.itis.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LocaleController {
    String changeLocale(String language, HttpServletRequest request, HttpServletResponse response);
}
