package org.billing.accountingpoints.service;

import lombok.RequiredArgsConstructor;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.billing.accountingpoints.utils.Queues;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = {Queues.SERVICE_NAME})
@Component
@RequiredArgsConstructor
public class ServiceStateConsumer {

  private final ServiceStateService service;

  @RabbitHandler
  public void receive(AccountingPointServiceStateDto dto) {
    service.changeState(dto);
  }
}
