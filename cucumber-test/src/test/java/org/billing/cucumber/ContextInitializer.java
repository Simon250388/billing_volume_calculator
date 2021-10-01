package org.billing.cucumber;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ContextInitializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {
  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {

//      ContainerBox.initTestContainers();
//
//      final TestPropertyValues testPropertyValues = TestPropertyValues.of(
//              "services.api.host="
//                      + ContainerBox.compose.getServiceHost("api", ContainerBox.API_PORT),
//              "services.api.port="
//                      + ContainerBox.compose.getServicePort("api", ContainerBox.API_PORT));
//
//      testPropertyValues.applyTo(applicationContext.getEnvironment());

  }
}
