package org.billing.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration(initializers = ContextInitializer.class)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        SecurityAutoConfiguration.class
})
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
