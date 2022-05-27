package org.billing.api.client;

import java.util.Collection;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.http.ResponseEntity;

public interface KeyRoomClient {
    ResponseEntity<Collection<KeyRoomResponse>> getAll();

    ResponseEntity<Object> create(KeyRoomRequest request);

    ResponseEntity<Object> update(String keyRoomId, KeyRoomRequest request);
}
