package org.billing.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceManagerService {

  private static final String OPERATION_MAPPING = "/service";
  private static final String OPERATION_CONNECT_MAPPING = "/connect";
  private static final String OPERATION_DISCONNECT_MAPPING = "/disconnect";

  @Qualifier("ServiceWebClient")
  private final WebClient webClient;

  public Mono<BillingServerResponse> connectService(ServiceRequest serviceRequest) {
    return webClient
        .post()
        .uri(String.join(OPERATION_MAPPING, OPERATION_CONNECT_MAPPING))
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(serviceRequest), ServiceRequest.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }

  public Mono<BillingServerResponse> disconnectService(ServiceRequest serviceRequest) {
    return webClient
        .post()
        .uri(String.join(OPERATION_MAPPING, OPERATION_DISCONNECT_MAPPING))
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(serviceRequest), ServiceRequest.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }
}
