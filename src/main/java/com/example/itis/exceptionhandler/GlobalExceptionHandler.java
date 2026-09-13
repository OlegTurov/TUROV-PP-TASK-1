package com.example.itis.exceptionhandler;

import com.example.itis.exception.UserAlreadyExistsException;
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
}
