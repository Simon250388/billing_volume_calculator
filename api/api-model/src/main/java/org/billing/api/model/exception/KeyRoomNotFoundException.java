package org.billing.api.model.exception;

public class KeyRoomNotFoundException extends IllegalArgumentException{
    public KeyRoomNotFoundException(String message) {
        super(message);
    }
}
