package org.billing.api.app.service;

import lombok.RequiredArgsConstructor;
import org.billing.api.app.auth.UserService;
import org.billing.api.model.exception.CheckPasswordException;
import org.billing.api.app.useCase.user.AuthRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordChecker {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public void checkOrElseThrow(AuthRequest request) {
        var userDetails = userService.loadUserByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new CheckPasswordException("Wrong password");
        }
    }

    public void isValidOrElseThrow(String password) {

    }
}
