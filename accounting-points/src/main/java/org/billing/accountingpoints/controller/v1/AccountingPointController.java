package org.billing.accountingpoints.controller.v1;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.billing.accountingpoints.request.BillingServerResponse;
import org.billing.accountingpoints.request.ChangeServiceStateRequest;
import org.billing.accountingpoints.service.GuidGenerator;
import org.billing.accountingpoints.service.ServiceStateService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounting-point")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingPointController {

  private final RabbitTemplate template;
  private final ServiceStateService serviceStateService;

  private final GuidGenerator guidGenerator;

  @Qualifier("serviceQueue")
  private final Queue queue;

  @GetMapping("/active/{keyRoomId}")
  public ResponseEntity<Collection<AccountingPointServiceStateDto>> getActive(
      @PathVariable UUID keyRoomId) {
    return ResponseEntity.ok(serviceStateService.currentActive(keyRoomId));
  }

  @GetMapping("/history/{keyRoomId}")
  public ResponseEntity<Collection<AccountingPointServiceStateDto>> getHistory(
      @PathVariable final UUID keyRoomId,
      @RequestParam(required=false) final Instant from,
      @RequestParam(required=false) final Instant to) {

    if (Optional.ofNullable(from).isPresent() && Optional.ofNullable(to).isPresent()) {
      return ResponseEntity.ok(serviceStateService.getHistory(keyRoomId, from, to));
    } else if (Optional.ofNullable(from).isPresent()) {
      return ResponseEntity.ok(serviceStateService.getHistory(keyRoomId, from));
    } else if (Optional.ofNullable(to).isPresent()) {
      throw new BillingValidationException("param from is empty");
    }
    return  ResponseEntity.ok(serviceStateService.getHistory(keyRoomId));
  }

  @PostMapping("/connect")
  public ResponseEntity<BillingServerResponse> connect(
      @RequestBody @Valid ChangeServiceStateRequest request) {

    UUID id = guidGenerator.randomUUID();

    template.convertAndSend(queue.getName(), getAccountingPointServiceStateDto(request, id, true));

    return ResponseEntity.ok(BillingServerResponse.builder().queryId(id).build());
  }

  @PostMapping("/disconnect")
  public ResponseEntity<BillingServerResponse> disconnect(
      @RequestBody ChangeServiceStateRequest request) {

    UUID id = guidGenerator.randomUUID();

    template.convertAndSend(queue.getName(), getAccountingPointServiceStateDto(request, id, false));

    return ResponseEntity.ok(BillingServerResponse.builder().queryId(id).build());
  }

  private AccountingPointServiceStateDto getAccountingPointServiceStateDto(
      ChangeServiceStateRequest request, UUID id, boolean active) {
    Instant periodFact;
    if (request.getPeriodFact() == null) {
      periodFact = null;
    } else {
      periodFact = request.getPeriodFact().toInstant();
    }

    return AccountingPointServiceStateDto.builder()
        .id(id)
        .accountPointId(request.getAccountingPoint().getId())
        .active(active)
        .keyRoomID(request.getKeyRoomId())
        .period(request.getPeriod().toInstant())
        .periodFact(periodFact)
        .serviceId(request.getServiceId())
        .build();
  }
}
