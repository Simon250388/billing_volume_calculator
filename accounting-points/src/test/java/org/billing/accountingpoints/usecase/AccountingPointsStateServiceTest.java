package org.billing.accountingpoints.usecase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDto;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDto;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.billing.accountingpoints.dto.ServiceImprovementTypeDto;
import org.billing.accountingpoints.model.MeterState;
import org.billing.accountingpoints.request.AccountingPointRequest;
import org.billing.accountingpoints.request.AccountingPointStateRequest;
import org.billing.accountingpoints.request.MeterRequest;
import org.billing.accountingpoints.request.RoomAccountingPointsRequest;
import org.billing.accountingpoints.request.ServiceProviderRequest;
import org.billing.accountingpoints.service.AccountingPointServiceProviderService;
import org.billing.accountingpoints.service.ImprovementTypeService;
import org.billing.accountingpoints.service.MeterStateService;
import org.billing.accountingpoints.service.ServiceStateService;
import org.billing.accountingpoints.usecase.convertor.ServiceProviderConvertor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {AccountingPointsStateService.class, ServiceProviderConvertor.class})
@Import(AccountingPointsStateServiceTest.TestConfig.class)
class AccountingPointsStateServiceTest {

  public static class TestConfig {
    @Bean
    Clock clock() {
      return Clock.fixed(Instant.parse("2022-01-01T00:00:00.00Z"), ZoneId.of("UTC"));
    }
  }

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

    final UUID uuid = UUID.randomUUID();

    final RoomAccountingPointsRequest result = accountingPointsStateService.currentState(uuid);

    final RoomAccountingPointsRequest expected =
        RoomAccountingPointsRequest.builder()
            .keyRoomId(uuid)
            .accountingPoints(
                Collections.singletonList(
                    AccountingPointStateRequest.builder()
                        .accountingPoint(
                            AccountingPointRequest.builder()
                                .id(UUID.fromString("dd897253-8af2-4b86-be21-e5bc69879672"))
                                .build())
                        .meter(
                            MeterRequest.builder()
                                .id(UUID.fromString("2e630ee6-5bd0-47a5-b810-e4b557afab94"))
                                .build())
                        .serviceProviders(
                            Collections.singletonList(
                                ServiceProviderRequest.builder()
                                    .serviceId(
                                        UUID.fromString("ad951f62-f29f-4031-be25-8273e358b504"))
                                    .providerId(
                                        UUID.fromString("eca9dbc6-42ff-4be5-a4d2-8b4e2cc57997"))
                                    .build()))
                        .build()))
            .build();

    assertThat(result, equalTo(expected));
  }

  private void setupMock() {
    when(serviceStateService.currentActive(any(UUID.class), any(Instant.class), any(Instant.class)))
        .thenReturn(
            Set.of(
                AccountingPointServiceStateDto.builder()
                    .id(UUID.fromString("e65c1e08-8685-4cc9-a87d-3c34c13320b4"))
                    .period(LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC))
                    .accountPointId(UUID.fromString("dd897253-8af2-4b86-be21-e5bc69879672"))
                    .serviceId(UUID.fromString("b04265bb-a2f1-48a2-a3ee-f72d1bfcc20d"))
                    .build()));

    when(meterStateService.currentStateByEntityServiceIdAsync(any(), any(Instant.class)))
        .thenReturn(
            CompletableFuture.completedFuture(
                Set.of(
                    AccountingPointMeterStateDto.builder()
                        .id(UUID.fromString("8a9af59d-445c-408c-8237-35f840e8daa3"))
                        .period(
                            LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC))
                        .accountPointId(UUID.fromString("dd897253-8af2-4b86-be21-e5bc69879672"))
                        .meterId(UUID.fromString("2e630ee6-5bd0-47a5-b810-e4b557afab94"))
                        .meterState(MeterState.ACTIVE)
                        .build())));

    when(serviceProviderService.currentProvidersAsync(any(), any(Instant.class)))
        .thenReturn(
            CompletableFuture.completedFuture(
                Set.of(
                    AccountingPointServiceProviderDto.builder()
                        .id(UUID.fromString("7ae75d1e-51ef-4599-9e11-94ac397627f2"))
                        .period(
                            LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC))
                        .accountPointId(UUID.fromString("dd897253-8af2-4b86-be21-e5bc69879672"))
                        .serviceId(UUID.fromString("ad951f62-f29f-4031-be25-8273e358b504"))
                        .providerId(UUID.fromString("eca9dbc6-42ff-4be5-a4d2-8b4e2cc57997"))
                        .build())));

    when(improvementTypeService.currentImprovementTypesAsync(any()))
        .thenReturn(
            CompletableFuture.completedFuture(
                Set.of(
                    ServiceImprovementTypeDto.builder()
                        .id(UUID.fromString("b4fdbb7f-ec24-4580-b5e2-5c5603d39968"))
                        .period(
                            LocalDate.parse("2021-08-27").atStartOfDay().toInstant(ZoneOffset.UTC))
                        .accountingPointId(UUID.fromString("dd897253-8af2-4b86-be21-e5bc69879672"))
                        .improvementTypeId(UUID.fromString("ae99bdff-a4bc-4a36-ba23-6b449d408bbb"))
                        .build())));
  }
}
