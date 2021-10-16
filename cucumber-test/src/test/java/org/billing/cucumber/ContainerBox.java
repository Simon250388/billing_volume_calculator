package org.billing.cucumber;

import java.io.File;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

public class ContainerBox {

  public static Integer API_PORT = 8080;

  @Container
  public static DockerComposeContainer compose =
      new DockerComposeContainer(new File("src/main/resources/docker-compose.yaml"))
          .withLocalCompose(true)
          .withExposedService("api", API_PORT, Wait.forListeningPort());

  public static void initTestContainers() {
    compose.start();
  }
}
