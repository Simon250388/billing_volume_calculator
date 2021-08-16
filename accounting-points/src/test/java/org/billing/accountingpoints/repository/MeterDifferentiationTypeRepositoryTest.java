package org.billing.accountingpoints.repository;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.billing.accountingpoints.dto.MeterDifferentiationTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false",
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.format_sql=true"
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("/db/common.xml")
@DatabaseSetup("/db/accounting-points.xml")
class MeterDifferentiationTypeRepositoryTest {

    private final MeterDifferentiationTypeRepository repository;

    @Autowired
    MeterDifferentiationTypeRepositoryTest(MeterDifferentiationTypeRepository repository) {
        this.repository = repository;
    }

    @Test
    @DatabaseSetup("/db/meter-differentiation-type.xml")
    void findAllLastByKeyRoomId() {
        final long keyRoomId = 1;
        List<MeterDifferentiationTypeDto> result = repository.findAllLastByKeyRoomId(keyRoomId);
        assertAll("",
                () -> assertEquals(1, result.size()),
                () -> assertEquals(2, result.get(0).getDifferentiationTypeId())
        );

    }
}
