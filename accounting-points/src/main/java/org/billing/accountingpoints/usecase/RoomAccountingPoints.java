package org.billing.accountingpoints.usecase;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;
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
  Long keyRoomId;

  @JsonProperty("period")
  @NotNull
  Instant period;

  @JsonProperty("periodFact")
  @Null
  Instant periodFact;

  @JsonProperty("accountingPoints")
  @NotNull
  List<AccountingPointStatePresentDto> accountingPointStatePresentDtos;
}
