package com.best.billing.servicebuilder.services.helpers.implementation;

import com.best.billing.servicebuilder.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.servicebuilder.models.enums.MeterState;
import com.best.billing.servicebuilder.models.historychange.*;
import com.best.billing.servicebuilder.repositories.historychange.*;
import com.best.billing.servicebuilder.services.helpers.ActiveAccountingPointDetails;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ActiveAccountingPointDetailsImpl implements ActiveAccountingPointDetails {
    private final AccountingPointServiceStateRepository accountingPointRepository;
    private final AccountingPointMeterStateRepository meterStateRepository;
    private final AccountingPointServiceProviderRepository pointProviderRepository;
    private final MeterDifferentiationTypeRepository meterDifferentiationTypeRepository;
    private final MeterValueRepository meterValueRepository;

    @Autowired
    public ActiveAccountingPointDetailsImpl(AccountingPointServiceStateRepository accountingPointRepository,
                                            AccountingPointMeterStateRepository meterStateRepository,
                                            AccountingPointServiceProviderRepository pointProviderRepository,
                                            MeterDifferentiationTypeRepository meterDifferentiationTypeRepository,
                                            MeterValueRepository meterValueRepository) {
        this.accountingPointRepository = accountingPointRepository;
        this.meterStateRepository = meterStateRepository;
        this.pointProviderRepository = pointProviderRepository;
        this.meterDifferentiationTypeRepository = meterDifferentiationTypeRepository;
        this.meterValueRepository = meterValueRepository;
    }

    @Override
    public Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NotNull Long keyRoomId) {
        Stream<AccountingPointServiceState> activeAccountingPoint = ImmutableList.copyOf(accountingPointRepository.findAllActiveByKeyRoomId(keyRoomId)).stream();
        Stream<AccountingPointMeterState> activeMeters = ImmutableList.copyOf(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).stream();
        Stream<AccountingPointServiceProvider> currentProviders = ImmutableList.copyOf(pointProviderRepository.findAllLastByKeyRoomId(keyRoomId)).stream();
        Stream<MeterDifferentiationType> currentDifferentiationTypes = ImmutableList.copyOf(meterDifferentiationTypeRepository.findAllLastByKeyRoomId(keyRoomId)).stream();
        Stream<MeterValue> currentMeterValues = ImmutableList.copyOf(meterValueRepository.findAllLastByKeyRoomId(keyRoomId)).stream();

        return activeAccountingPoint.map(activeService -> {
            final long accountingPointKeyRoomServiceEntityId = activeService.getAccountingPointKeyRoomServiceEntity().getId();
            ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder = ActiveAccountingPointDetailsDTO.builder();
            doSetServiceProperty(activeService, builder);
            doSetProviderProperty(builder, accountingPointKeyRoomServiceEntityId, currentProviders);
            doSetMeterProperty(builder, accountingPointKeyRoomServiceEntityId, activeMeters, currentDifferentiationTypes, currentMeterValues);
            return builder.build();
        }).collect(Collectors.toList());
    }

    private void doSetProviderProperty(
            @NotNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder,
            @NotNull Long accountingPointKeyRoomServiceEntityId,
            @NotNull Stream<AccountingPointServiceProvider> currentProviders) {

        currentProviders
                .filter(value -> value.getAccountingPointKeyRoomServiceEntity().getId().equals(accountingPointKeyRoomServiceEntityId))
                .forEach(value -> Optional.ofNullable(value.getServicePart())
                        .ifPresentOrElse(
                                servicePart -> builder.addServicePart(servicePart.getId(), value.getProvider().getId()),
                                () -> builder.providerId(value.getProvider().getId())));
    }

    private void doSetMeterProperty(
            @NotNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder,
            @NotNull Long accountingPointKeyRoomServiceEntityId,
            @NotNull Stream<AccountingPointMeterState> activeMetersStream,
            @NotNull Stream<MeterDifferentiationType> currentDifferentiationTypes,
            @NotNull Stream<MeterValue> currentMeterValues) {

        activeMetersStream
                .filter(value -> value.getAccountingPointKeyRoomServiceEntity().getId().equals(accountingPointKeyRoomServiceEntityId))
                .findFirst()
                .ifPresent(value -> {
                    builder.meterId(value.getMeter().getId());
                    builder.meterIsActive(value.getMeterState().getId() == MeterState.ACTIVE_STATE_ID);
                    builder.meterStateChangeAt(value.getPeriod());
                });
    }

    private void doSetServiceProperty(
            @NotNull AccountingPointServiceState activeService,
            @NotNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder) {

        builder
                .keyRoomId(activeService.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getKeyRoom().getId())
                .serviceId(activeService.getAccountingPointKeyRoomServiceEntity().getService().getId())
                .accountingPointId(activeService.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getAccountingPoint().getId())
                .isActive(activeService.isActive())
                .directionOfUseId(activeService.getAccountingPointKeyRoomServiceEntity().getDirectionOfUse().getId());
    }
}
