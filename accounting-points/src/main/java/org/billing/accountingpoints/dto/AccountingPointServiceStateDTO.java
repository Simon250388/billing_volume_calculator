package org.billing.accountingpoints.dto;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

public interface AccountingPointServiceStateDTO {

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
    @Value("#{target.active}")
    Boolean getServicePartId();

    boolean equals(Object o);

    int hashCode();
}
