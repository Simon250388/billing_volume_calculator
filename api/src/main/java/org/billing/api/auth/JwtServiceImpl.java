package org.billing.api.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

  @Value("$(jwt.secretKey)")
  private String jwtSecretKey;

  private final Clock clock;

  @Override
  public String generateToken(String login) {
    final Instant endSession = Instant.now(clock).plus(2, ChronoUnit.MINUTES);
    return Jwts.builder()
        .setSubject(login)
        .setExpiration(Date.from(endSession))
        .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
        .compact();
  }

  private Claims getClaims(String token) {
    return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
  }

  @Override
  public String getUsername(String token) {
    Claims claims = getClaims(token);
    return claims.getSubject();
  }
}
