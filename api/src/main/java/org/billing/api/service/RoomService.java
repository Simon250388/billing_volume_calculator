package org.billing.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.RoomPeopleCountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

  private static final String OPERATION_MAPPING = "/room";
  private static final String OPERATION_PRESCRIBED_MAPPING = "/prescribed";
  private static final String OPERATION_OWNER_MAPPING = "/owner";
  private static final String OPERATION_ROOM_PEOPLE_MAPPING = "/people";

  @Qualifier("RoomServiceWebClient")
  private final WebClient webClient;

  public Mono<BillingServerResponse> savePrescribedCount(
      RoomPeopleCountRequest roomPeopleCountRequest) {
    return webClient
        .post()
        .uri(String.join(OPERATION_MAPPING, OPERATION_PRESCRIBED_MAPPING))
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(roomPeopleCountRequest), RoomPeopleCountRequest.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }

  public Mono<BillingServerResponse> saveOwnerCount(RoomPeopleCountRequest roomPeopleCountRequest) {

    return webClient
        .post()
        .uri(String.join(OPERATION_MAPPING, OPERATION_OWNER_MAPPING))
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(roomPeopleCountRequest), RoomPeopleCountRequest.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }

  public Mono<BillingServerResponse> saveRoomPeopleCountRequest(
      RoomPeopleCountRequest roomPeopleCountRequest) {
    return webClient
        .post()
        .uri(String.join(OPERATION_MAPPING, OPERATION_ROOM_PEOPLE_MAPPING))
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(roomPeopleCountRequest), RoomPeopleCountRequest.class)
        .retrieve()
        .bodyToMono(BillingServerResponse.class);
  }
}
