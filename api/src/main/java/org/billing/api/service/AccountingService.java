package org.billing.api.service;

import org.billing.api.dto.AccountRequest;
import org.billing.api.dto.BillingServerResponse;
import org.springframework.scheduling.annotation.Async;
import reactor.core.publisher.Mono;

public interface AccountingService {
    @Async
    Mono<BillingServerResponse> createAccount(AccountRequest accountRequest);

    @Async
    Mono<BillingServerResponse> terminateAccount(AccountRequest accountRequest);

    @Async
    Mono<BillingServerResponse> changeAccountCustomer(AccountRequest accountRequest);
}
