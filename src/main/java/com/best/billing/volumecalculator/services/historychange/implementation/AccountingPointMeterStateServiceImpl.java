package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.volumecalculator.mappers.historychange.AccountingPointMeterStateMapper;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.services.historychange.AccountingPointMeterStateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AccountingPointMeterStateServiceImpl implements AccountingPointMeterStateService {
    private final AccountingPointMeterStateRepository repository;
    private final AccountingPointMeterStateMapper mapper;

    @Autowired
    public AccountingPointMeterStateServiceImpl(AccountingPointMeterStateRepository repository, AccountingPointMeterStateMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointMeterStateDTO save(AccountingPointMeterState accountingPoint) {
        return mapper.fromEntity(repository.save(accountingPoint));
    }

    @Override
    public Optional<AccountingPointMeterStateDTO> findById(long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<AccountingPointMeterStateDTO> doGetHistoryByAccountingPointKeyRoomService(long accountingPointKeyRoomService, long meterId) {
        return mapper.fromEntity(repository.findAllByAccountingPointKeyRoomServiceIdAndMeterId(accountingPointKeyRoomService, meterId));
    }

    @Override
    public Optional<AccountingPointMeterStateDTO> doGetLastByAccountingPointKeyRoomServiceIdAndMeterId(long accountingPointKeyRoomServiceId, long meterId) {
        return repository.findLastByAccountingPointKeyRoomServiceIdAndMeterId(accountingPointKeyRoomServiceId, meterId).map(mapper::fromEntity);
    }
}
