package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.AccountingPointServiceProviderDTO;
import com.best.billing.servicebuilder.mappers.historychange.AccountingPointServiceProviderMapper;
import com.best.billing.servicebuilder.repository.historychange.AccountingPointServiceProviderRepository;
import com.best.billing.servicebuilder.services.historychange.AccountingPointServiceProviderService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountingPointServiceProviderServiceImpl implements AccountingPointServiceProviderService {
    private final AccountingPointServiceProviderRepository repository;
    private final AccountingPointServiceProviderMapper mapper;

    @Autowired
    public AccountingPointServiceProviderServiceImpl(AccountingPointServiceProviderRepository repository, AccountingPointServiceProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointServiceProviderDTO save(@NonNull AccountingPointServiceProviderDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointServiceProviderDTO> findById(@NonNull Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
