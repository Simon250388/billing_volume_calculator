package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class MeterStep {

    @Autowired
    RequestBuilder requestBuilder;

    @Given("есть прибор учета с ключом {long}")
    public void setupLastAccountNumber(final Long meterId) {
        System.out.printf("есть прибор учета с ключом %s%n", meterId);
    }


}
