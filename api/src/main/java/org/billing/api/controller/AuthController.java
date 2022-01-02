package org.billing.api.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.billing.api.auth.JwtService;
import org.billing.api.auth.UserService;
import org.billing.api.request.AuthRequest;
import org.billing.api.request.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JwtService jwtService;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> auth(@RequestBody @Valid AuthRequest request) {

    final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());

    if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
      throw new IllegalArgumentException("Wrong password");
    }

    return ResponseEntity.ok(
        AuthResponse.builder().token(jwtService.generateToken(request.getUsername())).build());
  }
}
