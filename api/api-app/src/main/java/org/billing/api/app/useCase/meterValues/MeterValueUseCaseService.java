package org.billing.api.app.useCase.meterValues;

import java.util.Collection;
import java.util.List;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MeterValueUseCaseService {
    public ResponseEntity<Collection<MeterResponse>> getAll(@NonNull String keyRoomId) {
        return null;
    }

    public ResponseEntity<List<String>> getMeterValueHistory(@NonNull String keyRoomId, @NonNull String meterId) {
        return null;
    }

    public ResponseEntity<MeterValueResponse> saveValue(@NonNull MeterValueRequest request) {
        return null;
    }

    public ResponseEntity<MeterResponse> save(MeterRequest request) {
        return null;
    }
}
