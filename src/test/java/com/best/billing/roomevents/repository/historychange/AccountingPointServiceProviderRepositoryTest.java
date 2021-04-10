package com.best.billing.roomevents.repository.historychange;

import com.best.billing.roomevents.models.AccountingPointServiceProvider;
import com.best.billing.roomevents.repository.AccountingPointServiceProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

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
        Iterable<AccountingPointServiceProvider> allLastByKeyRoomId = this.repository.findAllLastByKeyRoomId(new Random().nextLong());
        assertFalse(allLastByKeyRoomId.iterator().hasNext());
    }
}
