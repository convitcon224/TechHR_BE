package com.usth.techhr.techhr.exception;

import org.springframework.dao.DataIntegrityViolationException;


public class UniqueConstraintViolatedException extends DataIntegrityViolationException {
    public UniqueConstraintViolatedException(String message) {
        super(message);
    }
}
