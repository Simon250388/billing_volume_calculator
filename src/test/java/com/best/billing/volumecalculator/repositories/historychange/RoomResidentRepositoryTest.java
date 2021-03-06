package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.catalog.Building;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.models.historychange.RoomResident;
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
import static org.mockito.ArgumentMatchers.*;

@DataJpaTest
class RoomResidentRepositoryTest {
    @Autowired
    private RoomResidentRepository repository;

    @Autowired
    private  EntityManager em;

    @Test
    void when_Table_Is_Empty_Then_FindOneLastByKeyRoomId_Should_Be_Empty() {
        Optional<RoomResident> roomOwners = repository.findOneLastByKeyRoomId(new Random().nextLong());
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
        calendar.set(2020, Calendar.JANUARY, 1);

        em.persist(RoomResident.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .residentCount(1)
                .build());

        Optional<RoomResident> roomResident = repository.findOneLastByKeyRoomId(keyRoom.getId());
        assertFalse(roomResident.isEmpty());
        RoomResident dataRow = roomResident.get();
        assertEquals(calendar.getTime(), dataRow.getPeriod());
        assertEquals(1, dataRow.getResidentCount());
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
        calendar.set(2019, Calendar.JANUARY, 1);

        em.persist(RoomResident.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .residentCount(1)
                .build());

        calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 1);

        em.persist(RoomResident.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .residentCount(15)
                .build());

        Optional<RoomResident> roomResident = repository.findOneLastByKeyRoomId(keyRoom.getId());
        assertFalse(roomResident.isEmpty());
        RoomResident dataRow = roomResident.get();
        assertEquals(calendar.getTime(), dataRow.getPeriod());
        assertEquals(15, dataRow.getResidentCount());
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
        calendar.set(2019, Calendar.JANUARY, 1);

        em.persist(RoomResident.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .residentCount(1)
                .build());

        calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 1);

        em.persist(RoomResident.builder()
                .keyRoom(keyRoom)
                .period(calendar.getTime())
                .residentCount(15)
                .build());

        Iterable<RoomResident> roomResidents = repository.findAllByKeyRoomId(keyRoom.getId());
        ImmutableList<RoomResident> roomOwnersList = ImmutableList.copyOf(roomResidents);
        assertEquals(2, roomOwnersList.size());
    }

}
