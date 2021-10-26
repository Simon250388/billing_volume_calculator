package org.billing.accountingpoints.request;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BillingServerError {
  String message;
  @Builder.Default
  List<Map.Entry<String, String>> errors = Collections.emptyList();
}
