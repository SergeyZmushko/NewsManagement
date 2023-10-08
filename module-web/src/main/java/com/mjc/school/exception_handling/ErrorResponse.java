package com.mjc.school.exception_handling;

public class ErrorResponse {
    private final String code;
    private final String message;
    private final String errorDetails;

    public ErrorResponse(String code, String message, String errorDetails){
        this.code = code;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
