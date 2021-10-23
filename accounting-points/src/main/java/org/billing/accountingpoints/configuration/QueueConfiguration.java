package org.billing.accountingpoints.configuration;

import org.billing.accountingpoints.utils.Queues;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

  @Bean
  @Qualifier("serviceQueue")
  public Queue serviceQueue() {
    return QueueBuilder.durable(Queues.SERVICE_NAME).build();
  }


}
