package com.best.billing.roomevents.services.historychange.implementation;

import com.best.billing.roomevents.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.roomevents.mappers.historychange.AccountingPointMeterStateMapper;
import com.best.billing.roomevents.repository.historychange.AccountingPointMeterStateRepository;
import com.best.billing.roomevents.services.historychange.AccountingPointMeterStateService;
import lombok.NonNull;
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
    public AccountingPointMeterStateDTO save(@NonNull final AccountingPointMeterStateDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointMeterStateDTO> findById(@NonNull final Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<AccountingPointMeterStateDTO> doGetHistoryByAccountingPointKeyRoomService(@NonNull final Long accountingPointKeyRoomServiceEntityId, @NonNull final Long meterId) {
        return mapper.fromEntity(repository.findAllByAccountingPointKeyRoomServiceEntityIdAndMeterId(accountingPointKeyRoomServiceEntityId, meterId));
    }

    @Override
    public Optional<AccountingPointMeterStateDTO> doGetLastByAccountingPointKeyRoomServiceIdAndMeterId(@NonNull final Long accountingPointKeyRoomServiceEntityId, @NonNull final Long meterId) {
        return repository.findOneLastAccountingPointKeyRoomServiceEntityIdAndMeterId(accountingPointKeyRoomServiceEntityId, meterId).map(mapper::fromEntity);
    }

    @Override
    public Iterable<AccountingPointMeterStateDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId) {
        return mapper.fromEntity(repository.findAllLastByKeyRoomId(keyRoomId));
    }
}
