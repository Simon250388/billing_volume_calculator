package com.best.billingvolumecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = {"com.best.billingvolumecalculator.models"})
@ComponentScan
public class BillingVolumeCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingVolumeCalculatorApplication.class, args);
	}

}
