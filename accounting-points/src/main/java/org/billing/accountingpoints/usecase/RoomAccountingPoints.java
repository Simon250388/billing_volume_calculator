package org.billing.accountingpoints.usecase;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;
import org.billing.accountingpoints.usecase.dto.AccountingPointStatePresentDto;

@Value
@Builder
public class RoomAccountingPoints {
  @JsonProperty("keyRoomId")
  @NotNull
  UUID keyRoomId;

  @JsonProperty("period")
  @NotNull
  OffsetDateTime period;

  @JsonProperty("periodFact")
  @Null
  OffsetDateTime periodFact;

  @JsonProperty("accountingPoints")
  @NotNull
  List<AccountingPointStatePresentDto> accountingPointStatePresentDtos;
}
