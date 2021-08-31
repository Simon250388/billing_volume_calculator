package org.billing.accountingpoints.service;

import java.util.Set;
import lombok.NonNull;
import org.billing.accountingpoints.dto.AccountingPointKeyRoomServiceEntityDto;

public interface AccountingPointKeyRoomServiceEntityService {
  Set<AccountingPointKeyRoomServiceEntityDto> findAllByKeyRoomId(@NonNull Long keyRoomId);

  Set<AccountingPointKeyRoomServiceEntityDto> findAllByKeyRoomIdAndServiceId(
      @NonNull Long keyRoomId, @NonNull Long serviceId);
}
