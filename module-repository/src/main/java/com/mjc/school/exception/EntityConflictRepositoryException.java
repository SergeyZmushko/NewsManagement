package com.mjc.school.exception;

public class EntityConflictRepositoryException extends RuntimeException{
    public EntityConflictRepositoryException(final String message) {
        super(message);
    }
}
