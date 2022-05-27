package org.billing.api.app.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class JwtValidationFilter extends OncePerRequestFilter {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  private final JwtService jwtService;
  private final UserServiceImpl userServiceImpl;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    //        final Optional<Authentication> authentication = Optional.ofNullable(
    //                SecurityContextHolder.getContext().getAuthentication());
    //
    //        if (authentication.isPresent()) {
    //            if (authentication.get().isAuthenticated()) {
    //                return;
    //            }
    //        }
    //
    //        String token = getToken(request);
    //
    //        if (StringUtils.isBlank(token)) {
    //            filterChain.doFilter(request, response);
    //            return;
    //        }
    //
    //        String username;
    //
    //        try {
    //            username = jwtService.getUsername(token);
    //        } catch (
    //                Exception exception) {
    //            filterChain.doFilter(request, response);
    //            return;
    //        }

    final UserDetails userDetails = userServiceImpl.loadUserByUsername("username");

    UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(auth);

    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    String fullToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.isNotBlank(fullToken) && fullToken.startsWith("Bearer ")) {
      return fullToken.split(" ")[1];
    }
    return null;
  }
}
