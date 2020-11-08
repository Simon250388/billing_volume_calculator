package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.common.model.Building;
import com.best.billing.common.model.enums.SquareType;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.RoomSquare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
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
        byte[] array = new byte[50];
        new Random().nextBytes(array);

        SquareType squareType = em.find(SquareType.class, SquareType.COMMON_SQUARE_TYPE_ID);

        Building building = Building.builder()
                .description(new String(array, Charset.forName("UTF-8")))
                .build();

        em.persist(building);

        KeyRoom keyRoom = KeyRoom.builder()
                .privateSector(new Random().nextBoolean())
                .room(null)
                .building(building)
                .build();

        em.persist(keyRoom);

        em.persist(RoomSquare.builder()
                .keyRoom(keyRoom)
                .period(LocalDateTime.of(2019, 1, 1, 0, 0, 0))
                .squareType(squareType)
                .value(30)
                .build());

        Optional<RoomSquare> commonSquare = repository.findOneLastCommonSquareByKeyRoomId(keyRoom.getId());
        assertFalse(commonSquare.isEmpty());
        RoomSquare dataRow = commonSquare.get();
        assertEquals(LocalDateTime.of(2019, 1, 1, 0, 0, 0), dataRow.getPeriod());
        assertEquals(30, dataRow.getValue());
    }

    @Test
    void when_Table_Contains_Many_Rows_Then_FindOneLastCommonSquareByKeyRoomId_Should_Be_Return_Row_Which_Have_Max_Period() {
        byte[] array = new byte[50];
        new Random().nextBytes(array);

        Building building = Building.builder()
                .description(new String(array, Charset.forName("UTF-8")))
                .build();

        em.persist(building);

        KeyRoom keyRoom = KeyRoom.builder()
                .privateSector(new Random().nextBoolean())
                .room(null)
                .building(building)
                .build();

        em.persist(keyRoom);

        SquareType squareType = em.find(SquareType.class, SquareType.COMMON_SQUARE_TYPE_ID);

        em.persist(RoomSquare.builder()
                .keyRoom(keyRoom)
                .period(LocalDateTime.of(2019, 1, 1, 0, 0, 0))
                .squareType(squareType)
                .value(30)
                .build());

        em.persist(RoomSquare.builder()
                .keyRoom(keyRoom)
                .period(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .squareType(squareType)
                .value(20)
                .build());

        Optional<RoomSquare> commonSquare = repository.findOneLastCommonSquareByKeyRoomId(keyRoom.getId());
        assertFalse(commonSquare.isEmpty());
        RoomSquare dataRow = commonSquare.get();
        assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0, 0), dataRow.getPeriod());
        assertEquals(20, dataRow.getValue());
    }
}
