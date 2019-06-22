package com.surveys.api.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotExistException extends Exception {
    public UserNotExistException(String message) {
        super(message);
    }
}
