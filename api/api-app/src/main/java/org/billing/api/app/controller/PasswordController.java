package org.billing.api.app.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.useCase.password.PasswordChangeRequest;
import org.billing.api.app.useCase.password.PasswordForgotRequest;
import org.billing.api.app.useCase.password.PasswordUseCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/password")
@RequiredArgsConstructor
public class PasswordController {

    private PasswordUseCaseService passwordUseCaseService;

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@RequestBody @Valid PasswordForgotRequest request) {
        return passwordUseCaseService.forgot(request);
    }

    @PostMapping("/change")
    public ResponseEntity<Void> change(@RequestBody @Valid PasswordChangeRequest request) {
        return passwordUseCaseService.change(request);
    }
}
