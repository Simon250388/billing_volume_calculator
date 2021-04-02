package com.best.billing.roomevents.repository.historychange;

import com.best.billing.common.model.Building;
import com.best.billing.roomevents.models.entity.KeyRoom;
import com.best.billing.roomevents.models.RoomOwner;
import com.google.common.collect.ImmutableList;
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

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(LocalDateTime.of(2020,1,1,0,0,0))
                .ownerCount(1)
                .build());

        Optional<RoomOwner> roomOwner = repository.findOneLastByKeyRoomId(keyRoom.getId());
        assertFalse(roomOwner.isEmpty());
        RoomOwner dataRow = roomOwner.get();
        assertEquals(LocalDateTime.of(2020,1,1,0,0,0), dataRow.getPeriod());
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

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(LocalDateTime.of(2019,1,1,0,0,0))
                .ownerCount(1)
                .build());

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(LocalDateTime.of(2020,2,1,0,0,0))
                .ownerCount(15)
                .build());

        Optional<RoomOwner> roomOwner = repository.findOneLastByKeyRoomId(keyRoom.getId());
        assertFalse(roomOwner.isEmpty());
        RoomOwner dataRow = roomOwner.get();
        assertEquals(LocalDateTime.of(2020,2,1,0,0,0), dataRow.getPeriod());
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

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(LocalDateTime.of(2019,2,1,0,0,0))
                .ownerCount(1)
                .build());

        em.persist(RoomOwner.builder()
                .keyRoom(keyRoom)
                .period(LocalDateTime.of(2020,2,1,0,0,0))
                .ownerCount(15)
                .build());

        Iterable<RoomOwner> roomOwners = repository.findAllByKeyRoomId(keyRoom.getId());
        ImmutableList<RoomOwner> roomOwnersList = ImmutableList.copyOf(roomOwners);
        assertEquals(2, roomOwnersList.size());
    }
}
