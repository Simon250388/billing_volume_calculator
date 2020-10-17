package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.catalog.Building;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.models.enums.SquareType;
import com.best.billing.volumecalculator.models.historychange.RoomSquare;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
class RoomSquareRepositoryTest {
    @Autowired
    private RoomSquareRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void When_Table_Is_Empty_Then_FindOneLastCommonSquareByKeyRoomId_Should_Be_Empty() {
        Optional<RoomSquare> commonSquare = repository.findOneLastCommonSquareByKeyRoomId(anyLong());
        assertTrue(commonSquare.isEmpty());
    }

    @Test
    void When_Table_Contains_MAny_Rows_Then_FindOneLastCommonSquareByKeyRoomId_Should_Be_Return_Row_Which_Have_Max_Period() {
        Building building = Building.builder()
                .description(anyString())
                .build();

        em.persist(building);

        KeyRoom keyRoom = KeyRoom.builder()
                .privateSector(anyBoolean())
                .room(null)
                .building(building)
                .build();

        em.persist(keyRoom);

        Calendar calendar;

        calendar = Calendar.getInstance();
        calendar.set(2019, 1, 1);

        em.persist(RoomSquare.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .squareType(
                        SquareType.builder().id(SquareType.COMMON_SQUARE_TYPE_ID).build()
                )
                .value(30)
                .build());

        calendar = Calendar.getInstance();
        calendar.set(2020, 1, 1);

        em.persist(RoomSquare.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .squareType(
                        SquareType.builder().id(SquareType.COMMON_SQUARE_TYPE_ID).build()
                )
                .value(20)
                .build());

        Optional<RoomSquare> commonSquare = repository.findOneLastCommonSquareByKeyRoomId(keyRoom.getId());
        assertFalse(commonSquare.isEmpty());
        RoomSquare dataRow = commonSquare.get();
        assertEquals(calendar.getTime(), dataRow.getPeriod());
        assertEquals(20, dataRow.getValue());
    }
}
