package org.billing.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
// @EnableJpaRepositories(basePackages = {"org.billing.accounts.repository"})
// @EntityScan(basePackages = {"org.billing.accounts.model"})
// @EnableTransactionManagement
@EnableAutoConfiguration(
    exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class AccountsApplication {
  public static void main(String[] args) {
    SpringApplication.run(AccountsApplication.class, args);
  }
}
