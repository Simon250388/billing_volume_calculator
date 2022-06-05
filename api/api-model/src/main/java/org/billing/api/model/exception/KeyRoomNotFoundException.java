package org.billing.api.model.exception;

public class KeyRoomNotFoundException extends IllegalArgumentException{
    public KeyRoomNotFoundException(String keyRoomId) {
        super(String.format("Помещение с ключом %s не найдено", keyRoomId));
    }
}
