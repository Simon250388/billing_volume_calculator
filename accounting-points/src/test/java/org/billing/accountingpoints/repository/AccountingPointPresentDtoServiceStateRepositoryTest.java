package org.billing.accountingpoints.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.List;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@DataJpaTest
@TestPropertySource(
    properties = {
      "spring.liquibase.enabled=false"
    })
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("/db/common.xml")
@DatabaseSetup("/db/accounting-points.xml")
class AccountingPointPresentDtoServiceStateRepositoryTest {

  @Autowired
  private AccountingPointServiceStateRepository repository;

  @Test
  @Tag("medium")
  @DatabaseSetup("/db/service-state.xml")
  void findAllActiveByKeyRoomId_WhenOneActive() {
    final long keyRoomId = 1;
    List<AccountingPointServiceStateDto> current = repository.findAllActiveByKeyRoomId(keyRoomId);
    assertEquals(1, current.size());
  }
}
