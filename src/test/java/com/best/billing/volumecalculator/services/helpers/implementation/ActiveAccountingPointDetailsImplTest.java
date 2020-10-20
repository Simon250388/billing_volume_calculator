package com.best.billing.volumecalculator.services.helpers.implementation;

import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.volumecalculator.models.catalog.AccountingPoint;
import com.best.billing.volumecalculator.models.catalog.DirectionOfUse;
import com.best.billing.volumecalculator.models.catalog.Service;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoom;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceStateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
class ActiveAccountingPointDetailsImplTest {
    @MockBean
    private AccountingPointServiceStateRepository accountingPointRepository;
    @MockBean
    private AccountingPointMeterStateRepository meterStateRepository;
    @Autowired
    private ActiveAccountingPointDetailsImpl service;
    @Autowired
    private EntityManager em;

    @Test
    void when_Table_Is_Empty_DoGetAllActiveByKeyRoomId_Should_Be_Size_0() {
        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(anyLong())).thenReturn(Collections.emptyList());
        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(anyLong())).thenReturn(Collections.emptyList());

        Iterable<ActiveAccountingPointDetailsDTO> activeAccountingPointDetailsDTOS = service.doGetAllActiveByKeyRoomId(new Random().nextLong());
        assertFalse(activeAccountingPointDetailsDTOS.iterator().hasNext());
    }

    @Test
    void when_Active_AccountingPoint_Exist_And_Active_Meters_Is_Empty_Then_DoGetAllActiveByKeyRoomId_Should_Be_Meter_State_False() {

        final long accountingPointId = new Random().nextLong();
        final long keyRoomId = new Random().nextLong();
        final long serviceId = new Random().nextLong();
        final long providerId = new Random().nextLong();
        final long directionOfUseId = new Random().nextLong();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);
        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(anyLong())).thenReturn(
                Collections.singletonList(
                        AccountingPointServiceState.builder()
                                .id(new Random().nextLong())
                                .accountingPointKeyRoomServiceEntity(
                                        AccountingPointKeyRoomServiceEntity.builder()
                                                .id(new Random().nextLong())
                                                .accountingPointKeyRoom(
                                                        AccountingPointKeyRoom.builder()
                                                                .keyRoom(
                                                                        KeyRoom.builder()
                                                                                .id(keyRoomId)
                                                                                .build()
                                                                )
                                                                .accountingPoint(
                                                                        AccountingPoint.builder()
                                                                                .id(accountingPointId)
                                                                                .build()
                                                                ).build()
                                                )
                                                .service(
                                                        Service.builder()
                                                                .id(serviceId)
                                                                .build())
                                                .directionOfUse(
                                                        DirectionOfUse.builder()
                                                                .id(directionOfUseId)
                                                                .build()
                                                )
                                                .build()
                                )
                                .active(true)
                                .period(calendar.getTime())
                                .build())
        );

        Calendar emptyDate = Calendar.getInstance();
        emptyDate.set(1, Calendar.JANUARY, 1);

        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(new Random().nextLong())).thenReturn(Collections.emptyList());
        Iterator<ActiveAccountingPointDetailsDTO> iterator = service.doGetAllActiveByKeyRoomId(new Random().nextLong()).iterator();
        assertTrue(iterator.hasNext());
        final ActiveAccountingPointDetailsDTO next = iterator.next();
        assertEquals(keyRoomId, next.getKeyRoomId());
        assertEquals(accountingPointId, next.getAccountingPointId());
        assertEquals(serviceId, next.getServiceId());
        assertEquals(providerId, next.getProviderId());
        assertEquals(directionOfUseId, next.getDirectionOfUseId());
        assertTrue(next.getIsActive());
        assertEquals(-1L, next.getMeterId());
        assertFalse(next.getMeterIsActive());
        assertEquals(emptyDate.getTime(), next.getMeterStateChangeAt());
        assertEquals(-1L, next.getDifferentiationTypeId());
        assertEquals(0, next.getLastMeterValue());
    }
}
