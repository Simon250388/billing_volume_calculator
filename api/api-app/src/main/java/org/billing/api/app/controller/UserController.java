package org.billing.api.app.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.useCase.user.AuthRequest;
import org.billing.api.app.useCase.user.AuthResponse;
import org.billing.api.app.useCase.user.UserUseCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCaseService userUseCaseService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> auth(@RequestBody @Valid AuthRequest request) {
        return userUseCaseService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRequest request) {
        return userUseCaseService.register(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody @Valid AuthRequest request) {
        return userUseCaseService.logout(request);
    }
}
