package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.servicebuilder.mappers.historychange.AccountingPointMeterStateMapper;
import com.best.billing.servicebuilder.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.servicebuilder.services.historychange.AccountingPointMeterStateService;
import org.jetbrains.annotations.NotNull;
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
    public AccountingPointMeterStateDTO save(@NotNull final AccountingPointMeterStateDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointMeterStateDTO> findById(@NotNull final Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<AccountingPointMeterStateDTO> doGetHistoryByAccountingPointKeyRoomService(@NotNull final Long accountingPointKeyRoomServiceEntityId, @NotNull final Long meterId) {
        return mapper.fromEntity(repository.findAllByAccountingPointKeyRoomServiceEntityIdAndMeterId(accountingPointKeyRoomServiceEntityId, meterId));
    }

    @Override
    public Optional<AccountingPointMeterStateDTO> doGetLastByAccountingPointKeyRoomServiceIdAndMeterId(@NotNull final Long accountingPointKeyRoomServiceEntityId, @NotNull final Long meterId) {
        return repository.findOneLastAccountingPointKeyRoomServiceEntityIdAndMeterId(accountingPointKeyRoomServiceEntityId, meterId).map(mapper::fromEntity);
    }

    @Override
    public Iterable<AccountingPointMeterStateDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId) {
        return mapper.fromEntity(repository.findAllLastByKeyRoomId(keyRoomId));
    }
}
