package org.billing.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@CucumberContextConfiguration
@SpringBootTest()
public class CucumberSpringConfiguration {
  @Configuration
  @EnableAutoConfiguration
  @ComponentScan("org.billing.cucumber")
  public static class TestConfig {
    @Bean
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }
  }
}
