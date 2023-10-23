package com.lothuialon.order.service.domain.exception;

import com.lothuialon.domain.exception.domainException;

public class orderDomainException extends domainException{

     public orderDomainException(String message) {
        super(message);

    }

    public orderDomainException(String message, Throwable cause) {
        super(message, cause);

    }
    
}
