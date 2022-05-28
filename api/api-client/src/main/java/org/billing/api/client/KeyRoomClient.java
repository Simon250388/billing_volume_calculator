package org.billing.api.client;

import java.util.Collection;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.http.ResponseEntity;

public interface KeyRoomClient {
    ResponseEntity<Collection<KeyRoomResponse>> getAllKeyRooms();

    ResponseEntity<Object> createKeyRoom(KeyRoomRequest request);

    ResponseEntity<Object> updateKeyRoom(String keyRoomId, KeyRoomRequest request);
}
