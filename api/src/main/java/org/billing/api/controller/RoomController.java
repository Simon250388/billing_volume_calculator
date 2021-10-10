package org.billing.api.controller;

import lombok.RequiredArgsConstructor;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.RoomPeopleCountRequest;
import org.billing.api.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/room")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

  private final RoomService roomService;

  @PostMapping("/prescribed")
  public Mono<ResponseEntity<BillingServerResponse>> savePrescribedCount(
      @RequestBody RoomPeopleCountRequest roomPeopleCountRequest) {
    return roomService.savePrescribedCount(roomPeopleCountRequest).map(ResponseEntity::ok);
  }

  @PostMapping("/owner")
  public Mono<ResponseEntity<BillingServerResponse>> saveOwnerCount(
      @RequestBody RoomPeopleCountRequest roomPeopleCountRequest) {
    return roomService.saveOwnerCount(roomPeopleCountRequest).map(ResponseEntity::ok);
  }

  @PostMapping("/resident")
  public Mono<ResponseEntity<BillingServerResponse>> saveResidentCount(
      @RequestBody RoomPeopleCountRequest roomPeopleCountRequest) {
    return roomService.saveOwnerCount(roomPeopleCountRequest).map(ResponseEntity::ok);
  }
}
