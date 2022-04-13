package com.example.blackpancake.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AdminOnlyException extends RuntimeException {
    public AdminOnlyException(String message) {
        super(message);
    }
}
