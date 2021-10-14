package org.billing.accountingpoints.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;
import org.billing.accountingpoints.model.AccountingPointServiceProvider;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@TestPropertySource(properties = {"spring.liquibase.enabled=false"})
class AccountingPointPresentDtoServiceProviderRepositoryTest {

  @Autowired private AccountingPointServiceProviderRepository repository;

  @Test
  @Tag("medium")
  @Sql({"classpath:db/common.sql", "classpath:db/accounting-points.sql"})
  @Sql("classpath:db/service-provider.sql")
  void findAllLastByKeyRoomId() {
    final UUID keyRoomId = UUID.fromString("7c3081d7-7d05-4cc0-9f79-0fac53a0a9e2");
    List<AccountingPointServiceProvider> result = repository.findAllLastByKeyRoomId(keyRoomId);
    AccountingPointServiceProvider expected =
        AccountingPointServiceProvider.builder()
            .id(UUID.fromString("5349b15c-9f7e-4658-ac6a-f83f05c86eaa"))
            .build();

    assertAll(
        () -> assertEquals(1, result.size()),
        () -> assertThat(result, containsInAnyOrder(hasProperty("id", equalTo(expected.getId())))));
  }
}
