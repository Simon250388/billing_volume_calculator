package org.billing.api.client;

import java.util.Collection;
import org.billing.api.model.keyRoom.KeyRoom;
import org.springframework.http.ResponseEntity;

public interface KeyRoomClient {
    ResponseEntity<Collection<KeyRoom>> getAllKeyRooms();

    ResponseEntity<Object> createKeyRoom(KeyRoom request);

    ResponseEntity<Object> updateKeyRoom(KeyRoom request);
}
