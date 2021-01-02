package com.best.billing.servicebuilder.services.helpers.implementation;

import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Provider;
import com.best.billing.common.model.Service;
import com.best.billing.common.model.enums.MeterState;
import com.best.billing.servicebuilder.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.servicebuilder.models.catalog.AccountingPoint;
import com.best.billing.servicebuilder.models.catalog.Meter;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoom;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceProvider;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceState;
import com.best.billing.servicebuilder.repository.historychange.AccountingPointMeterStateRepository;
import com.best.billing.servicebuilder.repository.historychange.AccountingPointServiceProviderRepository;
import com.best.billing.servicebuilder.repository.historychange.AccountingPointServiceStateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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
        final long keyRoomId = new Random().nextLong();
        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(keyRoomId)).thenReturn(Collections.emptyList());
        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).thenReturn(Collections.emptyList());
        Iterable<ActiveAccountingPointDetailsDTO> activeAccountingPointDetailsDTOS = service.doGetAllActiveByKeyRoomId(keyRoomId);
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
                                        .id(new Random().nextLong())
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

        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(keyRoomId)).thenReturn(
                Collections.singletonList(
                        AccountingPointServiceState.builder()
                                .id(random.nextLong())
                                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                                .active(true)
                                .period(LocalDateTime.of(2020, 2, 1, 0, 0, 0))
                                .build())
        );

        Calendar emptyDate = Calendar.getInstance();
        emptyDate.set(1, Calendar.JANUARY, 1);

        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).thenReturn(Collections.emptyList());
        Mockito.when(pointProviderRepository.findAllLastByKeyRoomId(keyRoomId)).thenReturn(Collections.singletonList(
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
        Iterator<ActiveAccountingPointDetailsDTO> iterator = service.doGetAllActiveByKeyRoomId(keyRoomId).iterator();
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


        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(keyRoomId)).thenReturn(
                Collections.singletonList(
                        AccountingPointServiceState.builder()
                                .id(random.nextLong())
                                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                                .active(true)
                                .period(LocalDateTime.of(2020, 2, 1, 0, 0, 0))
                                .build())
        );

        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).thenReturn(Collections.singletonList(
                AccountingPointMeterState.builder()
                        .id(random.nextLong())
                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                        .meter(
                                Meter.builder()
                                        .id(meterId)
                                        .build()
                        )
                        .meterState(MeterState.active)
                        .period(LocalDateTime.of(2020, 2, 1, 0, 0, 0))
                        .build()
        ));

        Mockito.when(pointProviderRepository.findAllLastByKeyRoomId(keyRoomId)).thenReturn(Collections.singletonList(
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
        Iterator<ActiveAccountingPointDetailsDTO> iterator = service.doGetAllActiveByKeyRoomId(keyRoomId).iterator();
        assertTrue(iterator.hasNext());
        final ActiveAccountingPointDetailsDTO next = iterator.next();
        assertEquals(keyRoomId, next.getKeyRoomId());
        assertEquals(accountingPointId, next.getAccountingPointId());
        assertEquals(serviceId, next.getServiceId());
        assertEquals(providerId, next.getProviderId());
        assertEquals(directionOfUseId, next.getDirectionOfUseId());
        assertTrue(next.getIsActive());
        assertEquals(meterId, next.getMeterId());
        assertTrue(next.getMeterIsActive());
        assertEquals(LocalDateTime.of(2020, 2, 1, 0, 0, 0), next.getMeterStateChangeAt());
        // TODO FIX ME
        // assertNull(next.getDifferentiationTypeId());
        // TODO FIX ME
        // assertNull(next.getLastMeterValue());
    }
}
