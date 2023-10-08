package com.mjc.school.exception_handling;

public class ApiVersionNotSupportedException extends RuntimeException{

    public ApiVersionNotSupportedException(String message){
        super(message);
    }

}
