package org.billing.accountingpoints.dto;




import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;


public interface AccountingPointServiceProviderDTO {
    @NotNull
    @Value("#{target.id}")
    long getId();
    @NotNull
    @Value("#{target.period}")
    LocalDateTime getPeriod();
    @Null
    @Value("#{target.period_fact}")
    LocalDateTime getPeriodFact();
    @NotNull
    @Value("#{target.accounting_point_key_room_service_id}")
    Long getAccountingPointKeyRoomServiceEntityId();
    @Null
    @Value("#{target.service_part_id}")
    Long getServicePartId();
    @NotNull
    @Value("#{target.provider_id}")
    long getProviderId();

    boolean equals(Object o);

    int hashCode();
}
