package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceState;
import com.best.billing.stabs.StabFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AccountingPointServiceStateRepositoryTest {

    @Autowired
    private AccountingPointServiceStateRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void When_Row_Exist_And_Is_Active_Then_Iterator_Has_Next_Should_Be_True() {
        StabFactory stabFactory = StabFactory.builder().build();
        em.persist(stabFactory.accountingPointServiceStateActive);
        KeyRoom keyRoom = stabFactory.accountingPointServiceStateActive.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getKeyRoom();
        Iterable<AccountingPointServiceState> current = repository.findAllActiveByKeyRoomId(keyRoom.getId());
        assertTrue(current.iterator().hasNext());
    }

    @Test
    void When_Last_Row_IS_Not_Active_Then_Iterator_Has_Next_Should_Be_False() {
        StabFactory stabFactory = StabFactory.builder().build();
        Arrays
                 .asList(stabFactory.accountingPointServiceStateActive, stabFactory.accountingPointServiceStateNotActive)
                 .forEach(em::persist);
        KeyRoom keyRoom = StabFactory.builder().build().accountingPointServiceStateActive.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getKeyRoom();
        Iterable<AccountingPointServiceState> current = repository.findAllActiveByKeyRoomId(keyRoom.getId());
        assertFalse(current.iterator().hasNext());
    }
}
