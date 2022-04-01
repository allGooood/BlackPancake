package com.example.blackpancake.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends IllegalArgumentException{
    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
