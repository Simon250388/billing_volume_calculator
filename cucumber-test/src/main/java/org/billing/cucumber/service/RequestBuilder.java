package org.billing.cucumber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestBuilder {

  @Autowired private RestTemplate restTemplate;

  private ResponseEntity<?> response;

  public ResponseEntity<?> getResponse() {
    return response;
  }

  public void sendGetForEntity(String url, Class<?> responseType) {
    response = restTemplate.getForEntity(url, responseType);
  }
}
