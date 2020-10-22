package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.catalog.Building;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@DataJpaTest
class RoomOwnerRepositoryTest {
    @Autowired
    private RoomOwnerRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void when_Table_Is_Empty_Then_FindOneLastByKeyRoomId_Should_Be_Empty() {
        Optional<RoomOwner> roomOwners = repository.findOneLastByKeyRoomId(anyLong());
        assertTrue(roomOwners.isEmpty());
    }

    @Test
    void when_Table_Contains_One_Row_Then_FindOneLastByKeyRoomId_Be_Return_Existing_Row() {

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

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .ownerCount(1)
                .build());

        Optional<RoomOwner> roomOwner = repository.findOneLastByKeyRoomId(keyRoom.getId());
        assertFalse(roomOwner.isEmpty());
        RoomOwner dataRow = roomOwner.get();
        assertEquals(calendar.getTime(), dataRow.getPeriod());
        assertEquals(1, dataRow.getOwnerCount());
    }

    @Test
    void when_Table_Contains_Many_Rows_Then_FindOneLastByKeyRoomId_Should_Be_Return_Row_Which_Have_Max_Period() {
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
        calendar.set(2019, Calendar.FEBRUARY, 1);

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .ownerCount(1)
                .build());

        calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .ownerCount(15)
                .build());

        Optional<RoomOwner> roomOwner = repository.findOneLastByKeyRoomId(keyRoom.getId());
        assertFalse(roomOwner.isEmpty());
        RoomOwner dataRow = roomOwner.get();
        assertEquals(calendar.getTime(), dataRow.getPeriod());
        assertEquals(15, dataRow.getOwnerCount());
    }

    @Test
    void when_Table_Contains_Many_Rows_Then_FindAllByKeyRoom_Id_Should_Be_Return_All_Rows() {
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
        calendar.set(2019, Calendar.FEBRUARY, 1);

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .ownerCount(1)
                .build());

        calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .ownerCount(15)
                .build());

        Iterable<RoomOwner> roomOwners = repository.findAllByKeyRoomId(keyRoom.getId());
        ImmutableList<RoomOwner> roomOwnersList = ImmutableList.copyOf(roomOwners);
        assertEquals(2, roomOwnersList.size());
    }
}
