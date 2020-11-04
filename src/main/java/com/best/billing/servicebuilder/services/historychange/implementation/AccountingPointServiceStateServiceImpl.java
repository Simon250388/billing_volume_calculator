package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.servicebuilder.mappers.historychange.AccountingPointServiceStateMapper;
import com.best.billing.servicebuilder.repository.historychange.AccountingPointServiceStateRepository;
import com.best.billing.servicebuilder.services.historychange.AccountingPointServiceStateService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountingPointServiceStateServiceImpl implements AccountingPointServiceStateService {
    private final AccountingPointServiceStateRepository repository;
    private final AccountingPointServiceStateMapper mapper;

    @Autowired
    public AccountingPointServiceStateServiceImpl(AccountingPointServiceStateRepository repository, AccountingPointServiceStateMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointServiceStateDTO save(@NonNull AccountingPointServiceStateDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointServiceStateDTO> findById(@NonNull Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
