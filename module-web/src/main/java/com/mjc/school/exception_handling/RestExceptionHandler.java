package com.mjc.school.exception_handling;

import com.mjc.school.service.exceptions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ApiVersionNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleApiVersionNotSupportedException(ApiVersionNotSupportedException exc) {
        return buildErrorResponse(API_VERSION_NOT_SUPPORTED.getErrorCode(), API_VERSION_NOT_SUPPORTED.getMessage(), exc.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUserNameNotFoundException(UsernameNotFoundException e) {
        return buildErrorResponse(USER_NOT_FOUND.getErrorCode(), USER_NOT_FOUND.getMessage(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailOrUserNameExistException.class)
    protected ResponseEntity<ErrorResponse> handleEmailOrUserNameException(EmailOrUserNameExistException e) {
        return buildErrorResponse(USER_NOT_FOUND.getErrorCode(), USER_NOT_FOUND.getMessage(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ValidatorException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException(Exception exc) {
        return buildErrorResponse(VALIDATION.getErrorCode(), String.format(VALIDATION.getMessage(), exc.getMessage()), exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception exc, WebRequest request) {
        return buildErrorResponse(RESOURCE_NOT_FOUND.getErrorCode(), RESOURCE_NOT_FOUND.getMessage(),
                exc.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceConflictServiceException.class)
    protected ResponseEntity<ErrorResponse> handleResourceConflictException(ServiceException exc) {
        return buildErrorResponse(exc.getCode(), exc.getMessage(),
                exc.getDetails(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorResponse> handleUnexpectedException(Exception exc) {
        return buildErrorResponse(UNEXPECTED_ERROR.getErrorCode(), UNEXPECTED_ERROR.getMessage(),
                exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String code, String errorMessage,
                                                             String errorDetails, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(code, errorMessage, errorDetails), status);
    }

}
