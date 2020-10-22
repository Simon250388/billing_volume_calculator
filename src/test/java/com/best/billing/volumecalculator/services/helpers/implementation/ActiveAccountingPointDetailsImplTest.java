package com.best.billing.volumecalculator.services.helpers.implementation;

import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.volumecalculator.models.catalog.*;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoom;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.models.enums.MeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceProvider;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceProviderRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceStateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    @MockBean
    private AccountingPointServiceProviderRepository pointProviderRepository;
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

    @DisplayName("Когда на точке учета никогда небыло установлено прибора учета тогда состояние прибора должны быть null")
    @Test
    void when_Active_AccountingPoint_Exist_And_Active_Meters_Is_Empty_Then_DoGetAllActiveByKeyRoomId_Should_Be_Meter_State_Null() {

        final long accountingPointId = new Random().nextLong();
        final long keyRoomId = new Random().nextLong();
        final long serviceId = new Random().nextLong();
        final long providerId = new Random().nextLong();
        final long directionOfUseId = new Random().nextLong();
        final Random random = new Random();

        AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity =
                AccountingPointKeyRoomServiceEntity.builder()
                        .id(random.nextLong())
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
                        .build();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);
        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(anyLong())).thenReturn(
                Collections.singletonList(
                        AccountingPointServiceState.builder()
                                .id(random.nextLong())
                                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                                .active(true)
                                .period(calendar.getTime())
                                .build())
        );

        Calendar emptyDate = Calendar.getInstance();
        emptyDate.set(1, Calendar.JANUARY, 1);

        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(anyLong())).thenReturn(Collections.emptyList());
        Mockito.when(pointProviderRepository.findAllLastByKeyRoomId(anyLong())).thenReturn(Collections.singletonList(
                AccountingPointServiceProvider.builder()
                        .id(random.nextLong())
                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                        .provider(
                                Provider.builder()
                                        .id(providerId)
                                        .build()
                        )
                        .build()
        ));
        Iterator<ActiveAccountingPointDetailsDTO> iterator = service.doGetAllActiveByKeyRoomId(random.nextLong()).iterator();
        assertTrue(iterator.hasNext());
        final ActiveAccountingPointDetailsDTO next = iterator.next();
        assertEquals(keyRoomId, next.getKeyRoomId());
        assertEquals(accountingPointId, next.getAccountingPointId());
        assertEquals(serviceId, next.getServiceId());
        assertEquals(providerId, next.getProviderId());
        assertEquals(directionOfUseId, next.getDirectionOfUseId());
        assertTrue(next.getIsActive());
        assertNull(next.getMeterId());
        assertNull(next.getMeterIsActive());
        assertNull(next.getMeterStateChangeAt());
        assertNull(next.getDifferentiationTypeId());
        assertNull(next.getLastMeterValue());
    }

    @DisplayName("Когда на точке учета установлен прибора учета тогда состояние прибора должны быть установлен")
    @Test
    void when_Active_AccountingPoint_Exist_And_Active_Meters_Is_Empty_Then_DoGetAllActiveByKeyRoomId_Should_Be_Meter_State_Is_Active() {

        final Random random = new Random();
        final long accountingPointId = random.nextLong();
        final long keyRoomId = random.nextLong();
        final long serviceId = random.nextLong();
        final long providerId = random.nextLong();
        final long directionOfUseId = random.nextLong();
        final long meterId = random.nextLong();

        AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity =
                AccountingPointKeyRoomServiceEntity.builder()
                        .id(random.nextLong())
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
                        .build();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);
        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(anyLong())).thenReturn(
                Collections.singletonList(
                        AccountingPointServiceState.builder()
                                .id(random.nextLong())
                                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                                .active(true)
                                .period(calendar.getTime())
                                .build())
        );

        Calendar emptyDate = Calendar.getInstance();
        emptyDate.set(1, Calendar.JANUARY, 1);

        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(anyLong())).thenReturn(Collections.singletonList(
                AccountingPointMeterState.builder()
                        .id(random.nextLong())
                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                        .meter(
                                Meter.builder()
                                        .id(meterId)
                                        .build()
                        )
                        .meterState(
                                MeterState.builder()
                                        .id(MeterState.ACTIVE_STATE_ID)
                                        .build()
                        )
                        .period(calendar.getTime())
                        .build()
        ));
        Mockito.when(pointProviderRepository.findAllLastByKeyRoomId(anyLong())).thenReturn(Collections.singletonList(
                AccountingPointServiceProvider.builder()
                        .id(random.nextLong())
                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                        .provider(
                                Provider.builder()
                                        .id(providerId)
                                        .build()
                        )
                        .build()
        ));
        Iterator<ActiveAccountingPointDetailsDTO> iterator = service.doGetAllActiveByKeyRoomId(random.nextLong()).iterator();
        assertTrue(iterator.hasNext());
        final ActiveAccountingPointDetailsDTO next = iterator.next();
        assertEquals(keyRoomId, next.getKeyRoomId());
        assertEquals(accountingPointId, next.getAccountingPointId());
        assertEquals(serviceId, next.getServiceId());
        assertEquals(providerId, next.getProviderId());
        assertEquals(directionOfUseId, next.getDirectionOfUseId());
        assertTrue(next.getIsActive());
        assertEquals(meterId,next.getMeterId());
        assertTrue(next.getMeterIsActive());
        assertEquals(calendar.getTime(),next.getMeterStateChangeAt());
        // TODO FIX ME
        // assertNull(next.getDifferentiationTypeId());
        // TODO FIX ME
        // assertNull(next.getLastMeterValue());
    }
}
