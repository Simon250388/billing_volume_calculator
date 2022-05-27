package org.billing.api.app.auth;

import org.billing.api.app.useCase.user.AuthRequest;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

  void save(AuthRequest request);

  void userNameIsUniqueOrElseThrow(String username);
}
