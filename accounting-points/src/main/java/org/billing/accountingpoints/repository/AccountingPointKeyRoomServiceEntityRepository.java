package org.billing.accountingpoints.repository;

import org.billing.accountingpoints.model.AccountingPointKeyRoomServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingPointKeyRoomServiceEntityRepository
    extends JpaRepository<AccountingPointKeyRoomServiceEntity, Long> {
}
