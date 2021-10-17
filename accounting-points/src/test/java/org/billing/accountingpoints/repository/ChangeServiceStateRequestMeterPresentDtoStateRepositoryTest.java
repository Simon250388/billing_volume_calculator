package org.billing.accountingpoints.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;
import org.billing.accountingpoints.model.AccountingPointMeterState;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@TestPropertySource(
    properties = {
      "spring.liquibase.enabled=false",
      "spring.jpa.hibernate.ddl-auto=create-drop",
    })
class ChangeServiceStateRequestMeterPresentDtoStateRepositoryTest {
  @Autowired private AccountingPointMeterStateRepository repository;

  @Test
  @Tag("medium")
  @Sql({"classpath:db/common.sql", "classpath:db/accounting-points.sql"})
  @Sql("classpath:db/accounting-point-meter-states.sql")
  void findAllLastByKeyRoomId() {

    final UUID keyRoomId = UUID.fromString("7c3081d7-7d05-4cc0-9f79-0fac53a0a9e2");

    final List<AccountingPointMeterState> result =
        repository.findAllLastActiveByKeyRoomId(keyRoomId);

    assertAll(
        "Some message",
        () -> assertEquals(1, result.size()),
        () ->
            assertEquals(
                UUID.fromString("1f867903-5f0e-4cfc-8b77-98845672db29"),
                result.get(0).getMeterId()));
  }
}
