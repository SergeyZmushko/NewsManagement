package com.mjc.school.exception;

public class SearchOperationNotFoundException extends RuntimeException{

    public SearchOperationNotFoundException() {}

    public SearchOperationNotFoundException(final String message) {
        super(message);
    }
}
