package com.professor_compilation.core.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchElementFoundException extends RuntimeException {

    public NoSuchElementFoundException(String message){
        super(message);
    }


}