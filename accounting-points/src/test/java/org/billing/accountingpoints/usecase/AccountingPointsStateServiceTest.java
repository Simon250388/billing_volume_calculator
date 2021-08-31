package org.billing.accountingpoints.usecase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDtoValue;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDtoValue;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDtoValue;
import org.billing.accountingpoints.dto.ServiceImprovementTypeDto;
import org.billing.accountingpoints.model.MeterState;
import org.billing.accountingpoints.service.AccountingPointServiceProviderService;
import org.billing.accountingpoints.service.ImprovementTypeService;
import org.billing.accountingpoints.service.MeterStateService;
import org.billing.accountingpoints.service.ServiceStateService;
import org.billing.accountingpoints.usecase.convertor.ServiceProviderConvertor;
import org.billing.accountingpoints.usecase.dto.AccountingPointStatePresentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {AccountingPointsStateService.class, ServiceProviderConvertor.class})
class AccountingPointsStateServiceTest {

  @MockBean private ServiceStateService serviceStateService;
  @MockBean private MeterStateService meterStateService;
  @MockBean private AccountingPointServiceProviderService serviceProviderService;
  @MockBean private ImprovementTypeService improvementTypeService;

  @Autowired private AccountingPointsStateService accountingPointsStateService;

  @BeforeEach
  public void init() {
    Mockito.reset(
        serviceStateService, meterStateService, serviceProviderService, improvementTypeService);
  }

  @Test
  void currentState() {
    setupMock();
    final RoomAccountingPoints roomAccountingPoints =
        accountingPointsStateService.currentState(
            1L,
            LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC),
            LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC));
    assertAll(
        () -> {
          assertEquals(1, roomAccountingPoints.getKeyRoomId());
          assertNotNull(roomAccountingPoints.getAccountingPointStatePresentDtos());
          assertEquals(1, roomAccountingPoints.getAccountingPointStatePresentDtos().size());

          final AccountingPointStatePresentDto accountingPointState =
              roomAccountingPoints.getAccountingPointStatePresentDtos().get(0);

          assertNotNull(accountingPointState.getAccountingPoint());
          assertNotNull(accountingPointState.getMeter());
          assertNotNull(accountingPointState.getServiceProviders());

          assertEquals(1, accountingPointState.getMeter().getId());
          assertEquals(1, accountingPointState.getServiceProviders().size());
        });
  }

  private void setupMock() {
    when(serviceStateService.currentActiveByKeyRoomId(
            anyLong(), any(Instant.class), any(Instant.class)))
        .thenReturn(
            Set.of(
                AccountingPointServiceStateDtoValue.builder()
                    .id(1L)
                    .period(LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC))
                    .accountPointId(1L)
                    .serviceId(1L)
                    .build()));

    when(meterStateService.currentStateByEntityServiceIdAsync(any(), any(Instant.class)))
        .thenReturn(
            CompletableFuture.completedFuture(
                Set.of(
                    AccountingPointMeterStateDtoValue.builder()
                        .id(1L)
                        .period(
                            LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC))
                        .accountPointId(1L)
                        .meterId(1L)
                        .meterState(MeterState.ACTIVE)
                        .build())));

    when(serviceProviderService.currentProvidersAsync(any(), any(Instant.class)))
        .thenReturn(
            CompletableFuture.completedFuture(
                Set.of(
                    AccountingPointServiceProviderDtoValue.builder()
                        .id(1L)
                        .period(
                            LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC))
                        .accountPointId(1L)
                        .serviceId(1L)
                        .providerId(1L)
                        .build())));

    when(improvementTypeService.currentImprovementTypesAsync(any()))
        .thenReturn(
            CompletableFuture.completedFuture(
                Set.of(
                    ServiceImprovementTypeDto.builder()
                        .id(1L)
                        .period(
                            LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC))
                        .accountingPointId(1L)
                        .improvementTypeId(1L)
                        .build())));
  }
}
