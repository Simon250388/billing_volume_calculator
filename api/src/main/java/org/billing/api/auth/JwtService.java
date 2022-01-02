package org.billing.api.auth;

public interface JwtService {

    String generateToken(String login);

    String getUsername(String token);
}
