package org.billing.common.repository;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false",
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.format_sql=true",
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("/db/common.xml")
class MeterValueRepositoryTest {
    @Autowired
    private MeterValueRepository repository;

//    @Test
//    void  when_Table_Is_Empty_findAllLastByKeyRoomId_Should_return_iterable_has_no_next() {
//        throw new UnsupportedOperationException("MeterValueRepositoryTest::when_Table_Is_Empty_findAllLastByKeyRoomId_Should_return_iterable_has_no_next");
//    }
}
