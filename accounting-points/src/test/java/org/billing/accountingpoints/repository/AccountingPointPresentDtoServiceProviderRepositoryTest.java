package org.billing.accountingpoints.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.List;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDto;
import org.junit.jupiter.api.Assertions;
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
      "spring.liquibase.enabled=false",
      "spring.jpa.show-sql=true",
      "spring.jpa.properties.hibernate.format_sql=true",
    })
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("/db/common.xml")
@DatabaseSetup("/db/accounting-points.xml")
class AccountingPointPresentDtoServiceProviderRepositoryTest {

  @Autowired private AccountingPointServiceProviderRepository repository;

  @Test
  @Tag("medium")
  @DatabaseSetup("/db/service-provider.xml")
  void findAllLastByKeyRoomId() {
    final long keyRoomId = 1;
    List<AccountingPointServiceProviderDto> allLastByKeyRoomId =
        repository.findAllLastByKeyRoomId(keyRoomId);
    Assertions.assertEquals(1, allLastByKeyRoomId.size());
    final long providerId = 2;
    assertEquals(providerId, allLastByKeyRoomId.get(0).getProviderId());
  }
}
