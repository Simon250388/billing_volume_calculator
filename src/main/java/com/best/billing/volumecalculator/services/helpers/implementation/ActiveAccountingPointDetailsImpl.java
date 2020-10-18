package com.best.billing.volumecalculator.services.helpers.implementation;

import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceStateRepository;
import com.best.billing.volumecalculator.services.helpers.ActiveAccountingPointDetails;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Stream;

@Service
public class ActiveAccountingPointDetailsImpl implements ActiveAccountingPointDetails {
    private final AccountingPointServiceStateRepository accountingPointRepository;
    private final AccountingPointMeterStateRepository meterStateRepository;

    @Autowired
    public ActiveAccountingPointDetailsImpl(AccountingPointServiceStateRepository accountingPointRepository, AccountingPointMeterStateRepository meterStateRepository) {
        this.accountingPointRepository = accountingPointRepository;
        this.meterStateRepository = meterStateRepository;
    }

    @Override
    public Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(long keyRoomId) {
            Stream<AccountingPointServiceState> activeAccountingPoint = ImmutableList.copyOf(accountingPointRepository.findAllActiveByKeyRoomId(keyRoomId)).stream();
            Stream<AccountingPointMeterState> activeMeters = ImmutableList.copyOf(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).stream();
            return Collections.<ActiveAccountingPointDetailsDTO>emptyList();
//        return activeAccountingPoint
//                .map(row ->
//                        activeMeters
//                                .filter(activeMeter -> activeMeter.getAccountingPointKeyRoomServiceEntity() == row.getAccountingPointKeyRoomServiceEntity())
//                                .findFirst()
//                                .map(activeMeter -> mapper.fromEntityAndMeterState(row, activeMeter))
//                                .orElse(mapper.fromEntity(row))
//                )
//                .collect(Collectors.toList());
    }
}
