package com.surveys.api.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class RatingFormatException extends Exception {
    public RatingFormatException(String message) {
        super(message);
    }
}
