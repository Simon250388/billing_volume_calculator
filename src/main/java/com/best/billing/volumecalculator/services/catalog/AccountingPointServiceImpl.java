package com.best.billing.volumecalculator.services.catalog;

import com.best.billing.volumecalculator.dto.catalog.AccountingPointDTO;
import com.best.billing.volumecalculator.mappers.catalog.AccountingPointMapper;
import com.best.billing.volumecalculator.models.catalog.AccountingPoint;
import com.best.billing.volumecalculator.repositories.catalog.AccountingPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountingPointServiceImpl implements BaseEntityService<AccountingPoint, AccountingPointDTO> {
    private final AccountingPointRepository repository;
    private final AccountingPointMapper mapper;

    @Autowired
    public AccountingPointServiceImpl(AccountingPointRepository repository, AccountingPointMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPoint save(AccountingPoint accountingPoint) {
        return this.repository.save(accountingPoint);
    }

    @Override
    public Optional<AccountingPointDTO> findById(long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }
}
