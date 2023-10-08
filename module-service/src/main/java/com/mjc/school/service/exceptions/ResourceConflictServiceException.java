package com.mjc.school.service.exceptions;

public class ResourceConflictServiceException extends ServiceException {

    public ResourceConflictServiceException(String message, String code, String details){
        super(message, code, details);
    }
}
