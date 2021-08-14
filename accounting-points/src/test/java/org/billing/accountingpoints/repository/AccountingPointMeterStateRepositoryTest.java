package org.billing.accountingpoints.repository;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@TestPropertySource(properties = {
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

    @Autowired
    private AccountingPointMeterStateRepository repository;

    @Test
    @DatabaseSetup("/db/accounting-point-meter-states.xml")
    void findAllLastByKeyRoomId() {
        long keyRoomId = 1;
        long meterId = 2;

        List<AccountingPointMeterStateDTO> allLastByKeyRoomId = repository.findAllLastActiveByKeyRoomId(keyRoomId);

        assertAll(
                () -> assertEquals(1, allLastByKeyRoomId.size()),
                () -> assertEquals(meterId, allLastByKeyRoomId.get(0).getMeterId())
        );

    }
}
