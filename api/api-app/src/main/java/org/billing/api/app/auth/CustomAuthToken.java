package org.billing.api.app.auth;

import java.util.List;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomAuthToken extends AbstractAuthenticationToken {

    public CustomAuthToken() {
        super(List.of(new SimpleGrantedAuthority("ADMIN")));
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return "UserPassword";
    }

    @Override
    public String getPrincipal() {
        return "userMame";
    }
}
