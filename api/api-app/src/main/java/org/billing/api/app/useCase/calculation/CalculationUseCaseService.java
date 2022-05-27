package org.billing.api.app.useCase.calculation;

import java.util.Collection;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculationUseCaseService {
    public ResponseEntity<Collection<CalculationResponse>> getAll(@NonNull final String keyRoomId) {
        return null;
    }

    public ResponseEntity<CalculationResponse> save(@NonNull final CalculationRequest request) {
        return null;
    }

    public ResponseEntity<CalculationResponse> update(@NonNull final String id, @NonNull final CalculationRequest request) {
        return null;
    }
}
