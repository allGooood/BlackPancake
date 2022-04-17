package com.example.blackpancake.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotMatchedException extends IllegalArgumentException{
    public UserNotMatchedException(String message) {
        super(message);
    }
}
