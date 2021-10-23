package org.billing.accountingpoints.service;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class BillingGuidGenerator implements GuidGenerator {
  public UUID randomUUID() {
    return UUID.randomUUID();
  }
}
