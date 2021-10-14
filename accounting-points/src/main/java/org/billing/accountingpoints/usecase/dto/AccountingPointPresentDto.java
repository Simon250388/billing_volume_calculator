package org.billing.accountingpoints.usecase.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountingPointPresentDto {
  @NotNull UUID id;
  @Null @Builder.Default String description = "";
}
