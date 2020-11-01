package com.best.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(
		basePackages = {
				"com.best.billing.common.repository",
				"com.best.billing.commonsettings.repository",
				"com.best.billing.servicebuilder.repository",
				"com.best.billing.volumecalculator.repository"
		})
@EntityScan(basePackages = {
		"com.best.billing.common",
		"com.best.billing.commonsettings",
		"com.best.billing.servicebuilder",
		"com.best.billing.volumecalculator",
})
@EnableTransactionManagement
@ComponentScan(
		basePackages = {
				"com.best.billing.common.controllers",
				"com.best.billing.commonsettings.controllers",
				"com.best.billing.servicebuilder.controllers",
				"com.best.billing.volumecalculator.controllers",

				"com.best.billing.common.services",
				"com.best.billing.commonsettings.services",
				"com.best.billing.servicebuilder.services",
				"com.best.billing.volumecalculator.services",

				"com.best.billing.common.mappers",
				"com.best.billing.commonsettings.mappers",
				"com.best.billing.servicebuilder.mappers",
				"com.best.billing.volumecalculator.mappers"
		}
)
public class BillingVolumeCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingVolumeCalculatorApplication.class, args);
	}

}
