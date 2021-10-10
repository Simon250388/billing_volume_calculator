package org.billing.api.controller;

import lombok.RequiredArgsConstructor;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.ServiceRequest;
import org.billing.api.service.ServiceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/service")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceController {

  private final ServiceManagerService serviceManager;

  @PostMapping("/connect")
  public Mono<ResponseEntity<BillingServerResponse>> connectService(
      @RequestBody ServiceRequest serviceRequest) {
    return serviceManager.connectService(serviceRequest).map(ResponseEntity::ok);
  }

  @PostMapping("/disconnect")
  public Mono<ResponseEntity<BillingServerResponse>> disconnectService(
      @RequestBody ServiceRequest serviceRequest) {
    return serviceManager.disconnectService(serviceRequest).map(ResponseEntity::ok);
  }
}
