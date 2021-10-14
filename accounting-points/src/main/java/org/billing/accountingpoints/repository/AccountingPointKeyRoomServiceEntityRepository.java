package org.billing.accountingpoints.repository;

import java.util.UUID;
import org.billing.accountingpoints.model.AccountingPointKeyRoomServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingPointKeyRoomServiceEntityRepository
    extends JpaRepository<AccountingPointKeyRoomServiceEntity, UUID> {
}
