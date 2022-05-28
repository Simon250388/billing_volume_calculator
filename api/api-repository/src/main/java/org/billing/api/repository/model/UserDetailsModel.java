package org.billing.api.repository.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsModel implements UserDetails {
  private static final long serialVersionUID = 5189173344056068628L;
  @Id
  private String username;
  private String password;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  @Builder.Default
  private boolean enabled = true;
  private Collection<GrantedAuthority> authorities;
}
