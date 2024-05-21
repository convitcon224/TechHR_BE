package com.usth.techhr.techhr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestErrorHandler {

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

}
