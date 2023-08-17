package com.sergiuszg.githubdetailsservice.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends GeneralException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
