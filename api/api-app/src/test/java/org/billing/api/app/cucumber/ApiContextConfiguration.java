package org.billing.api.app.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import or.billing.api.repository.KeyRoomDbService;
import or.billing.api.repository.MockKeyRoomRepository;
import org.billing.api.app.ApiApplication;
import org.billing.api.app.useCase.keyRoom.UserProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@CucumberContextConfiguration
@SpringBootTest(
    classes = ApiApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApiContextConfiguration {
  @Configuration
  public static class TestConfig {
    @Bean
    public KeyRoomDbService mockKeyRoomRepository() {
      return new MockKeyRoomRepository();
    }

    @Bean
    public UserProvider mockUserProvider() {
      return () -> "test";
    }
  }
}
