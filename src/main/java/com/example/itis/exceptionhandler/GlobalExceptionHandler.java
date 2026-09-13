package com.example.itis.exceptionhandler;

import com.example.itis.exception.UserAlreadyExistsException;
import com.example.itis.exception.UserNotExistsException;
import com.example.itis.exception.UserPasswordIncorrectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExists(
            UserAlreadyExistsException exception,
            Model model
    ) {
        model.addAttribute("error", exception.getMessage());
        return "register";
    }

    @ExceptionHandler(UserNotExistsException.class)
    public String handleUserNotExists(
            UserNotExistsException exception,
            Model model
    ) {
        model.addAttribute("error", exception.getMessage());
        return "login";
    }

    @ExceptionHandler(UserPasswordIncorrectException.class)
    public String handlePasswordIncorrect(
            UserPasswordIncorrectException exception,
            Model model
    ) {
        model.addAttribute("error", exception.getMessage());
        return "login";
    }
}
