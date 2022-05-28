package org.billing.api.app.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.billing.api.repository.KeyRoomDbService;
import org.billing.api.repository.MockKeyRoomRepository;
import org.billing.api.app.ApiApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@CucumberContextConfiguration
@SpringBootTest(
    classes = ApiApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApiContextConfiguration {}
