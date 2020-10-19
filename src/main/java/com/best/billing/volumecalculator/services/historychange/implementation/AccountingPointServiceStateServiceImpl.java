package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.volumecalculator.mappers.historychange.AccountingPointServiceStateMapper;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceStateRepository;
import com.best.billing.volumecalculator.services.historychange.AccountingPointServiceStateService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountingPointServiceStateServiceImpl implements AccountingPointServiceStateService {
    private final AccountingPointServiceStateRepository repository;
    private final AccountingPointServiceStateMapper mapper;

    public AccountingPointServiceStateServiceImpl(AccountingPointServiceStateRepository repository, AccountingPointServiceStateMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointServiceStateDTO save(AccountingPointServiceStateDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointServiceStateDTO> findById(long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
