package com.usth.techhr.techhr.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UniqueConstraintViolatedException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorDetails handleUniqueConstraintViolation(UniqueConstraintViolatedException ex) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDetails handleObjectNotFound(ObjectNotFoundException ex) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getFieldError().getDefaultMessage());
        return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorDetails handleAuthenticationException(AuthenticationException ex){
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage());
    }

}
