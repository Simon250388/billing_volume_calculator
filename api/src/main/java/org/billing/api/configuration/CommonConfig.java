package org.billing.api.configuration;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

  @Bean
  Clock clock() {
    return Clock.systemUTC();
  }
}
