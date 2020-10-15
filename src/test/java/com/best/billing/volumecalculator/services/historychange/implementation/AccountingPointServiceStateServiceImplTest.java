package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.volumecalculator.mappers.historychange.AccountingPointServiceStateMapper;
import com.best.billing.volumecalculator.models.catalog.Meter;
import com.best.billing.volumecalculator.models.catalog.MeterType;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.volumecalculator.models.enums.MeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceStateRepository;
import com.best.billing.volumecalculator.services.historychange.AccountingPointServiceStateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(SpringExtension.class)
class AccountingPointServiceStateServiceImplTest {
    @MockBean
    private AccountingPointServiceStateRepository repository;
    @MockBean
    private AccountingPointServiceStateMapper mapper;
    @MockBean
    private AccountingPointMeterStateRepository meterStateRepository;
    @InjectMocks
    private AccountingPointServiceStateServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new AccountingPointServiceStateServiceImpl(repository, mapper, meterStateRepository);
    }

    @Test
    void doGetAllActiveAccountingPointDetailByKeyRoomId() {

        Long KeyRoomId = anyLong();

        AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity = AccountingPointKeyRoomServiceEntity.builder().build();

        AccountingPointServiceState accountingPointServiceStateMock = AccountingPointServiceState.builder()
                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                .active(true)
                .period(new Date())
                .build();

        Mockito.when(repository.findAllActiveByKeyRoomId(KeyRoomId)).thenReturn(Arrays.asList(accountingPointServiceStateMock));

        Mockito.when(meterStateRepository.findAllLastByKeyRoomId(KeyRoomId)).thenReturn(Arrays.asList(
                AccountingPointMeterState.builder()
                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                        .meter(Meter.builder().build())
                        .meterState(MeterState.builder().id(MeterState.ACTIVE_STATE_ID).build())
                        .period(new Date())
                        .build()
        ));

        Iterable<AccountingPointServiceStateDTO> accountingPointServiceStateDTOS = service.doGetAllActiveAccountingPointDetailByKeyRoomId(KeyRoomId);
    }
}