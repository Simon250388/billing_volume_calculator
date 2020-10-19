package com.best.billing.volumecalculator.services.helpers.implementation;

import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.volumecalculator.models.enums.MeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceStateRepository;
import com.best.billing.volumecalculator.services.helpers.ActiveAccountingPointDetails;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Service
@Slf4j
public class ActiveAccountingPointDetailsImpl implements ActiveAccountingPointDetails {
    private final AccountingPointServiceStateRepository accountingPointRepository;
    private final AccountingPointMeterStateRepository meterStateRepository;

    @Autowired
    public ActiveAccountingPointDetailsImpl(AccountingPointServiceStateRepository accountingPointRepository, AccountingPointMeterStateRepository meterStateRepository) {
        this.accountingPointRepository = accountingPointRepository;
        this.meterStateRepository = meterStateRepository;
    }

    @Override
    public Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NotNull Long keyRoomId) {
        Stream<AccountingPointServiceState> activeAccountingPoint = ImmutableList.copyOf(accountingPointRepository.findAllActiveByKeyRoomId(keyRoomId)).stream();
        Stream<AccountingPointMeterState> activeMeters = ImmutableList.copyOf(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).stream();

        return activeAccountingPoint.map(activeService -> {
            ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder = ActiveAccountingPointDetailsDTO.builder();
            Optional<AccountingPointMeterState> activeMeterOptional = activeMeters
                    .filter(value -> value.getAccountingPointKeyRoomServiceEntity().getId() == activeService.getAccountingPointKeyRoomServiceEntity().getId())
                    .findFirst();

            doSetServiceProperty(keyRoomId, activeService, builder);
            doSetMeterProperty(builder, activeMeterOptional);
            return builder.build();
        }).collect(Collectors.toList());
    }

    private void doSetMeterProperty(@NotNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder, Optional<AccountingPointMeterState> activeMeterOptional) {
        if (activeMeterOptional.isPresent()) {
            AccountingPointMeterState activeMeter = activeMeterOptional.get();
            builder.meterId(activeMeter.getMeter().getId());
            builder.meterIsActive(activeMeter.getMeterState().getId() == MeterState.ACTIVE_STATE_ID);
            builder.meterStateChangeAt(activeMeter.getPeriod());
        } else {
            builder.meterId(-1L);
            builder.meterIsActive(false);
            builder.meterStateChangeAt(new Date(0));
        }
    }

    private void doSetServiceProperty(@NotNull Long keyRoomId, @NotNull AccountingPointServiceState activeService, @NotNull ActiveAccountingPointDetailsDTO.ActiveAccountingPointDetailsDTOBuilder builder) {
        builder
                .keyRoomId(keyRoomId)
                .serviceId(activeService.getAccountingPointKeyRoomServiceEntity().getService().getId())
                .accountingPointId(activeService.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getAccountingPoint().getId())
                //.providerId()
                .isActive(activeService.isActive())
                .directionOfUseId(activeService.getAccountingPointKeyRoomServiceEntity().getDirectionOfUse().getId());
    }
}
