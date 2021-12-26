package org.billing.api.auth;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean exist(String username);
    boolean exist(String username, String password);
    void save(String username);
}
