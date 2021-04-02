package com.best.billing.roomevents.services.helpers.implementation;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.roomevents.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.roomevents.models.*;
import com.best.billing.roomevents.repository.historychange.*;
import com.best.billing.roomevents.services.helpers.ActiveAccountingPointDetails;
import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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
    public Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NonNull Long keyRoomId) {
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
            @NonNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder,
            @NonNull Long accountingPointKeyRoomServiceEntityId,
            @NonNull Stream<AccountingPointServiceProvider> currentProviders) {

        currentProviders
                .filter(value -> value.getAccountingPointKeyRoomServiceEntity().getId() ==accountingPointKeyRoomServiceEntityId)
                .forEach(value -> Optional.ofNullable(value.getServicePart())
                        .ifPresentOrElse(
                                servicePart -> builder.addServicePart(servicePart.getId(), value.getProvider().getId()),
                                () -> builder.providerId(value.getProvider().getId())));
    }

    private void doSetMeterProperty(
            @NonNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder,
            @NonNull Long accountingPointKeyRoomServiceEntityId,
            @NonNull Stream<AccountingPointMeterState> activeMetersStream,
            @NonNull Stream<MeterDifferentiationType> currentDifferentiationTypes,
            @NonNull Stream<MeterValue> currentMeterValues) {

        activeMetersStream
                .filter(value -> value.getAccountingPointKeyRoomServiceEntity().getId() == accountingPointKeyRoomServiceEntityId)
                .findFirst()
                .ifPresent(value -> {
                    builder.meterId(value.getMeter().getId());
                    builder.meterIsActive(value.getMeterState() == MeterState.ACTIVE);
                    builder.meterStateChangeAt(value.getPeriod());
                });
    }

    private void doSetServiceProperty(
            @NonNull AccountingPointServiceState activeService,
            @NonNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder) {

        builder
                .keyRoomId(activeService.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getKeyRoom().getId())
                .serviceId(activeService.getAccountingPointKeyRoomServiceEntity().getService().getId())
                .accountingPointId(activeService.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getAccountingPoint().getId())
                .isActive(activeService.isActive())
                .directionOfUseId(activeService.getAccountingPointKeyRoomServiceEntity().getDirectionOfUse().getId());
    }
}
