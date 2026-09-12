package com.example.itis.exceptionhandler;

import com.example.itis.exception.UserAlreadyExistsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExists(
            UserAlreadyExistsException exception,
            Model model
    ) {
        model.addAttribute("error", exception.getMessage());

        return "register";
    }
}
