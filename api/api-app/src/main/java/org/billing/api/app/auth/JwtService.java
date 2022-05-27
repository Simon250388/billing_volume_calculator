package org.billing.api.app.auth;

import org.billing.api.app.useCase.user.AuthRequest;

public interface JwtService {

    String generateToken(String login);
    String generateToken(AuthRequest login);

    String getUsername(String token);
}
