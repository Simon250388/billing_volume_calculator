package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.volumecalculator.mappers.historychange.AccountingPointMeterStateMapper;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.services.historychange.AccountingPointMeterStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountingPointMeterStateServiceImpl implements AccountingPointMeterStateService {
    private final AccountingPointMeterStateRepository repository;
    private final AccountingPointMeterStateMapper mapper;

    @Autowired
    public AccountingPointMeterStateServiceImpl(AccountingPointMeterStateRepository repository, AccountingPointMeterStateMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointMeterStateDTO save(AccountingPointMeterStateDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointMeterStateDTO> findById(long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<AccountingPointMeterStateDTO> doGetHistoryByAccountingPointKeyRoomService(long accountingPointKeyRoomServiceEntityId, long meterId) {
        return mapper.fromEntity(repository.findAllByAccountingPointKeyRoomServiceEntityIdAndMeterId(accountingPointKeyRoomServiceEntityId, meterId));
    }

    @Override
    public Optional<AccountingPointMeterStateDTO> doGetLastByAccountingPointKeyRoomServiceIdAndMeterId(long accountingPointKeyRoomServiceEntityId, long meterId) {
        return repository.findOneLastAccountingPointKeyRoomServiceEntityIdAndMeterId(accountingPointKeyRoomServiceEntityId, meterId).map(mapper::fromEntity);
    }

    @Override
    public Iterable<AccountingPointMeterStateDTO> doGetLastByKeyRoomId(long keyRoomId) {
        return mapper.fromEntity(repository.findAllLastByKeyRoomId(keyRoomId));
    }
}
