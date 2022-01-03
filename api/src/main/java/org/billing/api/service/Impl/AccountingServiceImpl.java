package org.billing.api.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.api.dto.AccountRequest;
import org.billing.api.dto.AccountResponse;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingServiceImpl implements AccountingService {

  @Qualifier("AccountingServiceWebClient")
  private final WebClient webClient;

  @Override
  @Async
  public Mono<BillingServerResponse> createAccount(AccountRequest accountRequest) {
    return webClient
        .post()
        .uri("/account/create")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(accountRequest), AccountRequest.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }

  @Override
  @Async
  public Mono<BillingServerResponse> terminateAccount(AccountRequest accountRequest) {
    return webClient
        .post()
        .uri("/account/terminate")
        .body(accountRequest, AccountResponse.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }

  @Override
  @Async
  public Mono<BillingServerResponse> changeAccountCustomer(AccountRequest accountRequest) {
    return webClient
        .post()
        .uri("/account/change")
        .body(accountRequest, AccountResponse.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }
}
