package org.billing.accountingpoints.repository;

import java.util.List;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(
    properties = {
      "spring.liquibase.enabled=false",
      "spring.jpa.hibernate.ddl-auto=update",
      "spring.jpa.show-sql=true",
      "spring.jpa.properties.hibernate.format_sql=true",
    })
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("/db/common.xml")
@DatabaseSetup("/db/accounting-points.xml")
class AccountingPointMeterStateRepositoryTest {

  private final AccountingPointMeterStateRepository repository;

  @Autowired
  AccountingPointMeterStateRepositoryTest(AccountingPointMeterStateRepository repository) {
    this.repository = repository;
  }

  @Test
  @DatabaseSetup("/db/accounting-point-meter-states.xml")
  void findAllLastByKeyRoomId() {

    long keyRoomId = 1;

    final List<AccountingPointMeterStateDto> result = repository.findAllLastActiveByKeyRoomId(keyRoomId);

    assertAll(
        "Some message",
        () -> assertEquals(1, result.size()),
        () -> assertEquals(2, result.get(0).getMeterId()));
  }
}
