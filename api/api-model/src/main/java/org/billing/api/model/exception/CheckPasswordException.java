package org.billing.api.model.exception;

public class CheckPasswordException extends IllegalArgumentException{
    public CheckPasswordException(String message) {
        super(message);
    }
}
