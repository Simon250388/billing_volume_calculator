package org.billing.api.app.auth;

import java.util.Map;
import or.billing.api.repository.model.UserDetailsModel;
import org.billing.api.app.useCase.user.AuthRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private static final Map<String, String> USER_NAMES =
      Map.of("someusername", "$2a$10$GYWKoSOtXFLqpPWccUR/LOu/a4Tu4DBS552vz9xiEKPr09H.4dq1S");

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return UserDetailsModel.builder()
        .username("someusername")
        .password("$2a$10$GYWKoSOtXFLqpPWccUR/LOu/a4Tu4DBS552vz9xiEKPr09H.4dq1S")
        .build();
  }

  @Override
  public void save(AuthRequest request) {}

  @Override
  public void userNameIsUniqueOrElseThrow(String username) {}
}
