package org.billing.accountingpoints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = {
        "org.billing.common.model",
        "org.billing.rooms.model",
        "org.billing.accountingpoints.model"
})
public class AccountingPointsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountingPointsApplication.class, args);
    }
}
