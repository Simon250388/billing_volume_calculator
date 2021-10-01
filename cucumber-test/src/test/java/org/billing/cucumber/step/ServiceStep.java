package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceStep {

    @Autowired
    RequestBuilder requestBuilder;

    @Given("есть услуга с ключом {long}")
    public void setupLastAccountNumber(final Long serviceId) {
        System.out.printf("есть услуга с ключом %s%n", serviceId);
    }

}
