package org.billing.api.app.useCase.user;

import lombok.RequiredArgsConstructor;
import org.billing.api.app.auth.JwtService;
import org.billing.api.app.auth.UserService;
import org.billing.api.app.service.PasswordChecker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUseCaseService {
    private final JwtService jwtService;
    private final PasswordChecker passwordChecker;
    private final UserService userService;

    public ResponseEntity<AuthResponse> login(AuthRequest request) {
        passwordChecker.checkOrElseThrow(request);
        var token = jwtService.generateToken(request);
        return ResponseEntity.ok(AuthResponse.builder().token(token).build());
    }

    public ResponseEntity<AuthResponse> register(AuthRequest request) {
        userService.userNameIsUniqueOrElseThrow(request.getUsername());
        passwordChecker.isValidOrElseThrow(request.getPassword());
        userService.save(request);
        var token = jwtService.generateToken(request);
        return ResponseEntity.ok(AuthResponse.builder().token(token).build());
    }

    public ResponseEntity<Void> logout(AuthRequest request) {
        return ResponseEntity.noContent().build();
    }
}
