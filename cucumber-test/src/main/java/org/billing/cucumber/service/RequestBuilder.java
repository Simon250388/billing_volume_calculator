package org.billing.cucumber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestBuilder {

  @Value("${services.api.host}")
  private String apiHost;

  @Value("${services.api.port}")
  private String apiPort;

  @Autowired private RestTemplate restTemplate;

  private ResponseEntity<?> response;

  public <T> ResponseEntity<T> getResponse() {
    return (ResponseEntity<T>) response;
  }

  public void sendGetForEntity(String tmpUrl, Class<?> responseType) {
    response =
        restTemplate.getForEntity(
            String.format("http://%s:%s/%s", apiHost, apiPort, tmpUrl), responseType);
  }

  public void sendPostForEntity(String tmpUrl, Object request, Class<?> responseType) {
    response =
        restTemplate.postForEntity(
            String.format("http://%s:%s/%s", apiHost, apiPort, tmpUrl), request, responseType);
  }
}
