package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.common.model.enums.SquareType;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.RoomSquare;
import com.best.billing.stabs.StabFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoomSquareRepositoryTest {
    @Autowired
    private RoomSquareRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void when_Table_Is_Empty_Then_FindOneLastCommonSquareByKeyRoomId_Should_Be_Empty() {
        Optional<RoomSquare> commonSquare = repository.findOneLastCommonSquareByKeyRoomId(new Random().nextLong());
        assertTrue(commonSquare.isEmpty());
    }

    @Test
    void when_Table_Contains_One_Row_Then_FindOneLastCommonSquareByKeyRoomId_Should_Be_Return_Existing_Row() {
        StabFactory stabFactory = StabFactory.builder().build();
        em.persist(stabFactory.roomSquare30);
        KeyRoom keyRoom = stabFactory.roomSquare30.getKeyRoom();
        Optional<RoomSquare> commonSquare = repository.findOneLastCommonSquareByKeyRoomId(keyRoom.getId());
        assertTrue(commonSquare.isPresent());
        assertEquals(LocalDateTime.of(2019, 1, 1, 0, 0, 0), commonSquare.get().getPeriod());
        assertEquals(30, commonSquare.get().getValue());
    }

    @Test
    void when_Table_Contains_Many_Rows_Then_FindOneLastCommonSquareByKeyRoomId_Should_Be_Return_Row_Which_Have_Max_Period() {
        StabFactory stabFactory = StabFactory.builder().build();
        Arrays.asList(stabFactory.roomSquare30, stabFactory.roomSquare20).forEach(em::persist);
        KeyRoom keyRoom = stabFactory.roomSquare30.getKeyRoom();
        Optional<RoomSquare> commonSquare = repository.findOneLastCommonSquareByKeyRoomId(keyRoom.getId());
        assertFalse(commonSquare.isEmpty());
        RoomSquare dataRow = commonSquare.get();
        assertEquals(stabFactory.roomSquare20.getPeriod(), dataRow.getPeriod());
        assertEquals(stabFactory.roomSquare20.getValue(), dataRow.getValue());
    }
}
