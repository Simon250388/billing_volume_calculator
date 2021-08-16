package org.billing.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(
    basePackages = {
      "com.best.billing.common.repository",
      "com.best.billing.commonsettings.repository",
      "com.best.billing.roomevents",
      "com.best.billing.volumecalculator.repository"
    })
@EntityScan(
    basePackages = {
      "com.best.billing.calculationsettings",
      "com.best.billing.common",
      "com.best.billing.roomevents",
      "com.best.billing.volumecalculator",
    })
@EnableTransactionManagement
public class BillingCommonApplication {
  public static void main(String[] args) {
    SpringApplication.run(BillingCommonApplication.class, args);
  }
}
