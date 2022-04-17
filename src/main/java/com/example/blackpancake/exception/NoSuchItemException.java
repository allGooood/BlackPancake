package com.example.blackpancake.exception;


import java.util.NoSuchElementException;

public class NoSuchItemException extends NoSuchElementException {
    public NoSuchItemException(String message) {
        super(message);
    }
}
