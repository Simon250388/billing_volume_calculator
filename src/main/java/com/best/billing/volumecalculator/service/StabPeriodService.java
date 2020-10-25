package com.best.billing.volumecalculator.service;

import com.best.billing.volumecalculator.repository.StabPeriodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StabPeriodService {
    private final StabPeriodRepository repository;

    @Autowired
    public StabPeriodService(StabPeriodRepository repository) {
        this.repository = repository;
    }

    public void calculateVolume() {

    }
}
