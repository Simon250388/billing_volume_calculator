package org.billing.api.service;

import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.ServiceRequest;
import reactor.core.publisher.Mono;

public interface ServiceManagerService {
    Mono<BillingServerResponse> connectService(ServiceRequest serviceRequest);

    Mono<BillingServerResponse> disconnectService(ServiceRequest serviceRequest);
}
