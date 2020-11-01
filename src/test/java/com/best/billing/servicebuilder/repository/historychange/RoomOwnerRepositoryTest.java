package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.common.model.Building;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.RoomOwner;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoomOwnerRepositoryTest {
    @Autowired
    private RoomOwnerRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void when_Table_Is_Empty_Then_FindOneLastByKeyRoomId_Should_Be_Empty() {
        Optional<RoomOwner> roomOwners = repository.findOneLastByKeyRoomId(new Random().nextLong());
        assertTrue(roomOwners.isEmpty());
    }

    @Test
    void when_Table_Contains_One_Row_Then_FindOneLastByKeyRoomId_Be_Return_Existing_Row() {

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
