package org.billing.accountingpoints.request;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BillingServerError {
  String message;
  List<Map.Entry<String, String>> errors;
}
