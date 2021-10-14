package org.billing.metervalues.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;
import org.billing.metervalues.model.MeterDifferentiationType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@TestPropertySource(properties = {"spring.liquibase.enabled=false"})
class MeterDifferentiationTypeRepositoryTest {

  @Autowired private MeterDifferentiationTypeRepository repository;

  @Test
  @Tag("medium")
  @Sql("classpath:db/common.sql")
  @Sql("classpath:db/meter-differentiation-type.sql")
  void findAllLastByKeyRoomId() {
    final UUID keyRoomId = UUID.randomUUID();
    List<MeterDifferentiationType> result = repository.findAllLastByKeyRoomId(keyRoomId);
    MeterDifferentiationType expected = MeterDifferentiationType.builder().build();

    assertAll(() -> assertEquals(1, result.size()));
  }
}
