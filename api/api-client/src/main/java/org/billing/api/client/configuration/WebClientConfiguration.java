package org.billing.api.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
  public static final String AUTHORIZATION_HEADER = "Authorization";

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .defaultHeader(AUTHORIZATION_HEADER, String.format("Bearer %s", "testuser@test.com"))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("Accept", "*/*")
        .baseUrl("http://localhost:8085/api")
        .build();
  }
}
