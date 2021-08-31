package org.billing.accountingpoints.usecase.dto;

import javax.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MeterPresentDto {
  @Null Long id;
  @Null @Builder.Default String factoryNumber = "";
}
