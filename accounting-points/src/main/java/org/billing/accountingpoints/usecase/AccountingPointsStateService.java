package org.billing.accountingpoints.usecase;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDtoValue;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDtoValue;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDtoValue;
import org.billing.accountingpoints.service.AccountingPointServiceProviderService;
import org.billing.accountingpoints.service.ImprovementTypeService;
import org.billing.accountingpoints.service.MeterStateService;
import org.billing.accountingpoints.service.ServiceStateService;
import org.billing.accountingpoints.usecase.convertor.PresentDtoConvertor;
import org.billing.accountingpoints.usecase.dto.AccountingPointPresentDto;
import org.billing.accountingpoints.usecase.dto.AccountingPointStatePresentDto;
import org.billing.accountingpoints.usecase.dto.MeterPresentDto;
import org.billing.accountingpoints.usecase.dto.ServiceProviderPresentDto;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountingPointsStateService {
  private final ServiceStateService serviceStateService;
  private final MeterStateService meterStateService;
  private final AccountingPointServiceProviderService serviceProviderService;
  private final ImprovementTypeService improvementTypeService;
  private final PresentDtoConvertor<
          ServiceProviderPresentDto, AccountingPointServiceProviderDtoValue>
      serviceProviderConvertor;

  @NonNull
  public RoomAccountingPoints currentState(
      @NonNull Long keyRoomId, @NonNull Instant period, Instant periodFact) {

    final List<AccountingPointStatePresentDto> accountingPointStatePresentDtos =
        getTasksForBuildCurrentState(keyRoomId, period, periodFact);

    return RoomAccountingPoints.builder()
        .keyRoomId(keyRoomId)
        .accountingPointStatePresentDtos(accountingPointStatePresentDtos)
        .build();
  }

  private List<AccountingPointStatePresentDto> getTasksForBuildCurrentState(
      Long keyRoomId, Instant period, Instant periodFact) {

    final Set<AccountingPointServiceStateDtoValue> allEntityServiceId =
        serviceStateService.currentActiveByKeyRoomId(keyRoomId, period, periodFact);

    final Map<Long, AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder>
        accountingPointStateBuilderMap = getBuildersMap(allEntityServiceId);

    final CompletableFuture[] tasks =
        List.of(
                taskForMeterState(accountingPointStateBuilderMap),
                taskForServiceProviders(accountingPointStateBuilderMap),
                taskForServiceImprovementTypes(allEntityServiceId, accountingPointStateBuilderMap))
            .toArray(new CompletableFuture[0]);

    CompletableFuture.allOf(tasks);

    return accountingPointStateBuilderMap.values().stream()
        .map(AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder::build)
        .collect(Collectors.toList());
  }

  private Map<Long, AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder>
      getBuildersMap(Set<AccountingPointServiceStateDtoValue> allEntityServiceId) {
    return allEntityServiceId.stream()
        .collect(
            Collectors.toMap(
                AccountingPointServiceStateDtoValue::getAccountPointId,
                this::createAccountingPointBuilder));
  }

  private AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder
      createAccountingPointBuilder(AccountingPointServiceStateDtoValue item) {
    return AccountingPointStatePresentDto.builder()
        .accountingPoint(AccountingPointPresentDto.builder().id(item.getAccountPointId()).build());
  }

  private CompletableFuture<?> taskForMeterState(
      Map<Long, AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder> builders) {
    return meterStateService
        .currentStateByEntityServiceIdAsync(builders.keySet(), Instant.now())
        .thenAccept(result -> setMeter(result, builders));
  }

  private CompletableFuture<?> taskForServiceProviders(
      Map<Long, AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder> builders) {
    return serviceProviderService
        .currentProvidersAsync(builders.keySet(), Instant.now())
        .thenAccept(result -> setServiceProviders(result, builders));
  }

  private CompletableFuture<?> taskForServiceImprovementTypes(
      Set<AccountingPointServiceStateDtoValue> accountingPointServicesStateDto,
      Map<Long, AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder> builders) {
    return improvementTypeService
        .currentImprovementTypesAsync(
            accountingPointServicesStateDto.stream()
                .map(AccountingPointServiceStateDtoValue::getServiceId)
                .collect(Collectors.toSet()))
        .thenAccept((result) -> {});
  }

  private void setMeter(
      Set<AccountingPointMeterStateDtoValue> inputSet,
      Map<Long, AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder> builders) {
    inputSet.forEach(
        item ->
            builders
                .get(item.getAccountPointId())
                .meter(MeterPresentDto.builder().id(item.getMeterId()).build())
                .build());
  }

  private void setServiceProviders(
      Set<AccountingPointServiceProviderDtoValue> result,
      Map<Long, AccountingPointStatePresentDto.AccountingPointStatePresentDtoBuilder> builders) {
    result.stream()
        .collect(
            Collectors.groupingBy(
                AccountingPointServiceProviderDtoValue::getAccountPointId, Collectors.toSet()))
        .forEach(
            (key, value) ->
                builders.get(key).serviceProviders(serviceProviderConvertor.toPresent(value)));
  }
}
