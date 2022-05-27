package org.billing.api.app.useCase.password;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordUseCaseService {
    public ResponseEntity<Void> forgot(PasswordForgotRequest request) {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> change(PasswordChangeRequest request) {
        return ResponseEntity.noContent().build();
    }
}
