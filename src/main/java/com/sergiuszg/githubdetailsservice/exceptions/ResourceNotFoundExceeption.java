package com.sergiuszg.githubdetailsservice.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundExceeption extends GeneralException {

    public ResourceNotFoundExceeption(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
