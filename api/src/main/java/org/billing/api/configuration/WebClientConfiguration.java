package org.billing.api.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

  @Bean
  @Qualifier("AccountingServiceWebClient")
  public WebClient accountingServiceWebClient() {
    return WebClient.builder()
        .baseUrl("http://localhost:8080")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Bean
  @Qualifier("MeterValueServiceWebClient")
  public WebClient meterValueServiceWebClient() {
    return WebClient.builder()
        .baseUrl("http://localhost:8080")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Bean
  @Qualifier("RoomServiceWebClient")
  public WebClient roomServiceWebClient() {
    return WebClient.builder()
        .baseUrl("http://localhost:8080")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Bean
  @Qualifier("ServiceWebClient")
  public WebClient serviceWebClient() {
    return WebClient.builder()
        .baseUrl("http://localhost:8080")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
