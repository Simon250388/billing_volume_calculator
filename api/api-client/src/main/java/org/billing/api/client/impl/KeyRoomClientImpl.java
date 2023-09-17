package org.billing.api.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.billing.api.client.KeyRoomClient;
import org.billing.api.model.keyRoom.KeyRoom;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KeyRoomClientImpl implements KeyRoomClient {

  private final WebClient webClient;
  private final ObjectMapper mapper;
  private final String HANDLE_PATH = "/v1/key-room";

  @Override
  public ResponseEntity<Collection<KeyRoom>> getAllKeyRooms() {

    return webClient
        .get()
        .uri(HANDLE_PATH)
        .retrieve()
        .toEntity(new ParameterizedTypeReference<Collection<KeyRoom>>() {})
        .block();
  }

  @Override
  public ResponseEntity<Object> createKeyRoom(KeyRoom request) {
    return webClient
        .post()
        .uri(HANDLE_PATH)
        .body(BodyInserters.fromValue(request))
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {})
        .onErrorResume(
            WebClientResponseException.class,
            ex -> {
              if (ex.getStatusCode().is4xxClientError()) {
                try {
                  Map<String, String> body =
                      mapper.readValue(
                          ex.getResponseBodyAsString(StandardCharsets.UTF_8), Map.class);
                  return Mono.just(ResponseEntity.badRequest().body(body));
                } catch (JsonProcessingException e) {
                  return Mono.error(ex);
                }
              } else {
                return Mono.error(ex);
              }
            })
        .block();
  }

  @Override
  public ResponseEntity<Object> updateKeyRoom(KeyRoom request) {
    return webClient
        .put()
        .uri(HANDLE_PATH)
        .body(BodyInserters.fromValue(request))
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {})
        .onErrorResume(
            WebClientResponseException.class,
            ex -> {
              if (ex.getStatusCode().is4xxClientError()) {
                try {
                  Map<String, String> body =
                      mapper.readValue(
                          ex.getResponseBodyAsString(StandardCharsets.UTF_8), Map.class);
                  return Mono.just(ResponseEntity.badRequest().body(body));
                } catch (JsonProcessingException e) {
                  return Mono.error(ex);
                }
              } else {
                return Mono.error(ex);
              }
            })
        .block();
  }
}
