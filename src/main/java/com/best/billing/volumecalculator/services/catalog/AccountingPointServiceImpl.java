package com.best.billing.volumecalculator.services.catalog;

import com.best.billing.volumecalculator.models.catalog.AccountingPoint;
import com.best.billing.volumecalculator.repositories.catalog.AccountingPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountingPointServiceImpl implements BaseEntityService<AccountingPoint> {
    private final AccountingPointRepository repository;

    @Autowired
    public AccountingPointServiceImpl(AccountingPointRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountingPoint save(AccountingPoint accountingPoint) {
        return this.repository.save(accountingPoint);
    }

    @Override
    public Optional<AccountingPoint> findById(long id) {
        return this.repository.findById(id);
    }
}
