package com.mjc.school.service.exceptions;

public class EmailOrUserNameExistException extends RuntimeException {
    public EmailOrUserNameExistException(String message) {
        super(message);
    }
}
