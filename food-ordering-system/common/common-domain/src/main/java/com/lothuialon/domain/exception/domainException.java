package com.lothuialon.domain.exception;

public class domainException extends RuntimeException{
    
    public domainException(String message, Throwable cause) {
        super(message, cause);
    }

    public domainException(String message) {
        super(message);
    }
}
