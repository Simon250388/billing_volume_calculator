package com.best.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.best.billing.volumecalculator.repositories"})
@EntityScan(basePackages = {"com.best.billing.volumecalculator.models"})
@EnableTransactionManagement
@ComponentScan
public class BillingVolumeCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingVolumeCalculatorApplication.class, args);
	}

}
