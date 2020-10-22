package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.MeterDifferentiationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class MeterDifferentiationTypeRepositoryTest {

    @Autowired
    private MeterDifferentiationTypeRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void when_Table_Is_Empty_findAllLastByKeyRoomId_Should_return_iterable_has_no_next() {
        Iterable<MeterDifferentiationType> result = repository.findAllLastByKeyRoomId(new Random().nextLong());
        assertFalse(result.iterator().hasNext());
    }
}