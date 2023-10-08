package com.mjc.school.service.exceptions;

public enum ServiceErrorCode {
    NEWS_ID_DOES_NOT_EXIST(Constants.ERROR_000001, "News with id %d does not exist."),
    AUTHOR_ID_DOES_NOT_EXIST(Constants.ERROR_000002, "Author Id does not exist. Author Id is: %s"),
    TAG_ID_DOES_NOT_EXIST(Constants.ERROR_000003, "Tag Id does not exist. Tag Id is: %s"),
    AUTHOR_DOES_NOT_EXIST_FOR_NEWS_ID(Constants.ERROR_000004, "Author not found for news with id %d."),
    COMMENT_ID_DOES_NOT_EXIST(Constants.ERROR_000005, "Comment with id %d does not exist."),
    VALIDATION(Constants.ERROR_000013, "Validation failed: %s"),
    RESOURCE_NOT_FOUND(Constants.ERROR_000014, "Resource not found"),
    API_VERSION_NOT_SUPPORTED(Constants.ERROR_000016, "This API version is not supported."),
    AUTHOR_CONFLICT(Constants.ERROR_000021, "Author has a persistence conflict due to " +
            "entity id existence."),
    UNEXPECTED_ERROR(Constants.ERROR_000050, "Unexpected error happened on server."),
    NEWS_CONFLICT(Constants.ERROR_000031, "News has a persistence conflict due to " +
            "entity id existence."),
    TAG_CONFLICT(Constants.ERROR_000041, "Tag has a persistence conflict due to " +
            "entity id existence."),
    COMMENT_CONFLICT(Constants.ERROR_000051, "Comment has a persistence conflict due to " +
            "entity id existence.");

    private final String errorMessage;
    private final String errorCode;

    ServiceErrorCode(String errorCode, String message) {
        this.errorMessage = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    private static class Constants {
        private static final String ERROR_000001 = "000001";
        private static final String ERROR_000002 = "000002";
        private static final String ERROR_000003 = "000003";
        private static final String ERROR_000004 = "000004";
        private static final String ERROR_000005 = "000005";
        private static final String ERROR_000013 = "000013";
        private static final String ERROR_000014 = "000014";
        private static final String ERROR_000016 = "000016";
        private static final String ERROR_000021 = "000021";
        private static final String ERROR_000050 = "000050";
        private static final String ERROR_000031 = "000031";
        private static final String ERROR_000041 = "000041";
        private static final String ERROR_000051 = "000051";

        private Constants() {
        }
    }
}
