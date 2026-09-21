package com.example.itis.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class LocaleControllerImpl implements LocaleController {

    private final LocaleResolver localeResolver;

    @GetMapping("/locale/{language}")
    public String changeLocale(@PathVariable String language, HttpServletRequest request, HttpServletResponse response) {
        Locale locale = Locale.forLanguageTag(language);
        localeResolver.setLocale(request, response, locale);

        return "redirect:" + request.getHeader("Referer");
    }
}
