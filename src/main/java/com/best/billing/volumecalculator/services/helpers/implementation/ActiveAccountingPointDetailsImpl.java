package com.best.billing.volumecalculator.services.helpers.implementation;

import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.volumecalculator.models.enums.MeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceProvider;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceProviderRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceStateRepository;
import com.best.billing.volumecalculator.services.helpers.ActiveAccountingPointDetails;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ActiveAccountingPointDetailsImpl implements ActiveAccountingPointDetails {
    private final AccountingPointServiceStateRepository accountingPointRepository;
    private final AccountingPointMeterStateRepository meterStateRepository;
    private final AccountingPointServiceProviderRepository pointProviderRepository;

    @Autowired
    public ActiveAccountingPointDetailsImpl(AccountingPointServiceStateRepository accountingPointRepository, AccountingPointMeterStateRepository meterStateRepository, AccountingPointServiceProviderRepository pointProviderRepository) {
        this.accountingPointRepository = accountingPointRepository;
        this.meterStateRepository = meterStateRepository;
        this.pointProviderRepository = pointProviderRepository;
    }

    @Override
    public Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NotNull Long keyRoomId) {
        Stream<AccountingPointServiceState> activeAccountingPoint = ImmutableList.copyOf(accountingPointRepository.findAllActiveByKeyRoomId(keyRoomId)).stream();
        Stream<AccountingPointMeterState> activeMeters = ImmutableList.copyOf(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).stream();
        Stream<AccountingPointServiceProvider> currentProviders = ImmutableList.copyOf(pointProviderRepository.findAllLastByKeyRoomId(keyRoomId)).stream();

        return activeAccountingPoint.map(activeService -> {
            ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder = ActiveAccountingPointDetailsDTO.builder();

            doSetServiceProperty(activeService, builder);
            doSetProviderProperty(builder, activeService.getAccountingPointKeyRoomServiceEntity().getId(), currentProviders);
            doSetMeterProperty(builder, activeService.getAccountingPointKeyRoomServiceEntity().getId(), activeMeters);
            return builder.build();
        }).collect(Collectors.toList());
    }

    private void doSetProviderProperty(@NotNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder, @NotNull Long accountingPointKeyRoomServiceEntityId, @NotNull Stream<AccountingPointServiceProvider> currentProviders) {
        currentProviders
                .filter(value -> value.getAccountingPointKeyRoomServiceEntity().getId() == accountingPointKeyRoomServiceEntityId)
                .forEach(value -> {
                    if (value.getServicePart() == null) {
                        builder.providerId(value.getProvider().getId());
                    } else {
                        builder.addServicePart(value.getServicePart().getId(), value.getProvider().getId());
                    }
                });
    }

    private void doSetMeterProperty(@NotNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder, @NotNull Long accountingPointKeyRoomServiceEntityId, @NotNull Stream<AccountingPointMeterState> activeMetersStream) {
        activeMetersStream
                .filter(value -> value.getAccountingPointKeyRoomServiceEntity().getId() == accountingPointKeyRoomServiceEntityId)
                .findFirst()
                .ifPresent(value -> {
                    builder.meterId(value.getMeter().getId());
                    builder.meterIsActive(value.getMeterState().getId() == MeterState.ACTIVE_STATE_ID);
                    builder.meterStateChangeAt(value.getPeriod());
                });
    }

    private void doSetServiceProperty(@NotNull AccountingPointServiceState activeService, @NotNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder) {
        builder
                .keyRoomId(activeService.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getKeyRoom().getId())
                .serviceId(activeService.getAccountingPointKeyRoomServiceEntity().getService().getId())
                .accountingPointId(activeService.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getAccountingPoint().getId())
                .isActive(activeService.isActive())
                .directionOfUseId(activeService.getAccountingPointKeyRoomServiceEntity().getDirectionOfUse().getId());
    }
}
