package org.billing.api.auth;

import java.util.Map;
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
  public boolean exist(String username) {
    return USER_NAMES.containsKey(username);
  }

  @Override
  public boolean exist(String username, String password) {
    return USER_NAMES.containsKey(username) && USER_NAMES.get(username).equals(password);
  }

  @Override
  public void save(String username) {}
}
