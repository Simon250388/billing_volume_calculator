package org.billing.accountingpoints.controller.v1;

import java.time.Instant;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.billing.accountingpoints.request.BillingServerError;
import org.billing.accountingpoints.request.BillingServerResponse;
import org.billing.accountingpoints.request.ChangeServiceStateRequest;
import org.billing.accountingpoints.service.GuidGenerator;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounting-point")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingPointController {

  private final RabbitTemplate template;

  private final GuidGenerator guidGenerator;

  @Qualifier("serviceQueue")
  private final Queue queue;

  @PostMapping("/connect")
  public ResponseEntity<BillingServerResponse> connect(@RequestBody @Valid ChangeServiceStateRequest request) {

    UUID id = guidGenerator.randomUUID();

    template.convertAndSend(queue.getName(), getAccountingPointServiceStateDto(request, id, true));

    return ResponseEntity.ok(BillingServerResponse.builder().queryId(id).build());
  }

  @PostMapping("/disconnect")
  public ResponseEntity<BillingServerResponse> disconnect(@RequestBody ChangeServiceStateRequest request) {

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
