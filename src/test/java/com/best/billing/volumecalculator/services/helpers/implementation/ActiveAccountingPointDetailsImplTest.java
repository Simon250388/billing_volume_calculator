package com.best.billing.volumecalculator.services.helpers.implementation;

import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.volumecalculator.models.catalog.AccountingPoint;
import com.best.billing.volumecalculator.models.catalog.DirectionOfUse;
import com.best.billing.volumecalculator.models.catalog.Service;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoom;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
        Long keyRoomId = new Random().nextLong();
        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(keyRoomId)).thenReturn(Collections.emptyList());
        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).thenReturn(Collections.emptyList());

        Iterable<ActiveAccountingPointDetailsDTO> activeAccountingPointDetailsDTOS = service.doGetAllActiveByKeyRoomId(keyRoomId);
        assertFalse(activeAccountingPointDetailsDTOS.iterator().hasNext());
    }

    @Test
    void when_Active_AccountingPoint_Exist_And_Active_Meters_Is_Empty_Then_DoGetAllActiveByKeyRoomId_Should_Be_Meter_State_False() {


        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 1);
        Mockito.when(accountingPointRepository.findAllActiveByKeyRoomId(new Random().nextLong())).thenReturn(
                Collections.singletonList(
                        AccountingPointServiceState.builder()
                                .id(new Random().nextLong())
                                .accountingPointKeyRoomServiceEntity(
                                        AccountingPointKeyRoomServiceEntity.builder()
                                                .id(new Random().nextLong())
                                                .accountingPointKeyRoom(
                                                        AccountingPointKeyRoom.builder()
                                                                .accountingPoint(
                                                                        AccountingPoint.builder()
                                                                                .id(new Random().nextLong())
                                                                                .build()
                                                                ).build()
                                                )
                                                .service(
                                                        Service.builder()
                                                                .id(new Random().nextLong())
                                                                .build())
                                                .directionOfUse(
                                                        DirectionOfUse.builder()
                                                                .id(new Random().nextLong())
                                                                .build()
                                                )
                                                .build()
                                )
                                .active(true)
                                .period(calendar.getTime())
                                .build())
        );
        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(new Random().nextLong())).thenReturn(Collections.emptyList());

        Iterable<ActiveAccountingPointDetailsDTO> activeAccountingPointDetailsDTOS = service.doGetAllActiveByKeyRoomId(new Random().nextLong());
        assertTrue(activeAccountingPointDetailsDTOS.iterator().hasNext());
    }
}