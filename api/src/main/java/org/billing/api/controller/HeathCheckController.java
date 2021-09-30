package org.billing.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HeathCheckController {
  @GetMapping
  public ResponseEntity<String> ping() {
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
}
