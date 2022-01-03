package org.billing.api.service.Impl;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.MeterValueRequest;
import org.billing.api.service.MeterValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeterValueServiceImpl implements MeterValueService {

  private static final String OPERATION_MAPPING = "/meter-value";

  @Qualifier("MeterValueServiceWebClient")
  private final WebClient webClient;

  @Override
  public List<String> meterValueHistory(Long meterId) {
    return Collections.emptyList();
  }

  @Override
  public Mono<BillingServerResponse> saveMeterValue(MeterValueRequest meterValueRequest) {
    return webClient
        .post()
        .uri(OPERATION_MAPPING)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(meterValueRequest), MeterValueRequest.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }
}
