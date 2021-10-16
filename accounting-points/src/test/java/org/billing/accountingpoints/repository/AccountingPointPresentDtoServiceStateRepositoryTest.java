package org.billing.accountingpoints.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;
import org.billing.accountingpoints.model.AccountingPointServiceState;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@TestPropertySource(
    properties = {
      "spring.liquibase.enabled=false"
    })
class AccountingPointPresentDtoServiceStateRepositoryTest {

  @Autowired
  private AccountingPointServiceStateRepository repository;

  @Test
  @Tag("medium")
  @Sql({"classpath:db/common.sql", "classpath:db/accounting-points.sql"})
  @Sql("classpath:db/service-state.sql")
  void findAllActiveByKeyRoomId_WhenOneActive() {
    final UUID keyRoomId = UUID.fromString("7c3081d7-7d05-4cc0-9f79-0fac53a0a9e2");
    List<AccountingPointServiceState> current = repository.findAllActive(keyRoomId);
    assertEquals(1, current.size());
  }
}
