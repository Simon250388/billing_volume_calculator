package org.billing.api.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.MeterValueRequest;
import org.billing.api.service.MeterValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/meter-value")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeterValueController {

  private final MeterValueService meterValueService;

  @GetMapping("/meterId")
  public ResponseEntity<List<String>> getMeterValueHistory(@PathVariable Long meterId) {
    return ResponseEntity.ok(meterValueService.meterValueHistory(meterId));
  }

  @PostMapping
  public Mono<ResponseEntity<BillingServerResponse>> saveMeterValue(
      @RequestBody MeterValueRequest meterValueRequest) {
    return meterValueService
        .saveMeterValue(meterValueRequest)
        .map(ResponseEntity::ok);
  }
}
