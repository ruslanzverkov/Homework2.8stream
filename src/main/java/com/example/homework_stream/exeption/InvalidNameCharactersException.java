package com.example.homework_stream.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNameCharactersException extends RuntimeException {


    public InvalidNameCharactersException(String message) {
        super(message);
    }
}
