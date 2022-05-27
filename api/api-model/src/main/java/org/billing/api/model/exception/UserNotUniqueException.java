package org.billing.api.model.exception;

public class UserNotUniqueException extends IllegalArgumentException {
    public UserNotUniqueException(String message) {
        super(message);
    }
}
