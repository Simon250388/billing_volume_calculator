package org.billing.api.service;

import java.util.List;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.MeterValueRequest;
import reactor.core.publisher.Mono;

public interface MeterValueService {
    List<String> meterValueHistory(Long meterId);

    Mono<BillingServerResponse> saveMeterValue(MeterValueRequest meterValueRequest);
}
