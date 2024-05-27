package com.usth.techhr.techhr.exception;

public class UniqueConstraintViolatedException extends RuntimeException {
    public UniqueConstraintViolatedException(String message) {
        super(message);
    }
}
