package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.constraints.AssertFalse;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountingPointServiceProviderRepositoryTest {

    @Autowired
    private AccountingPointServiceProviderRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void When_Table_Is_Empty_Then_findAllLastByKeyRoomId_Should_Return_Iterable_Size_0() {
        Iterable<AccountingPointServiceProvider> allLastByKeyRoomId = repository.findAllLastByKeyRoomId(new Random().nextLong());
        assertFalse(allLastByKeyRoomId.iterator().hasNext());
    }
}