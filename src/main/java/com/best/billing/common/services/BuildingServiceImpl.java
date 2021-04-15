package com.best.billing.common.services;

import com.best.billing.common.model.Building;
import com.best.billing.common.repository.BuildingRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository repository;

    @Autowired
    public BuildingServiceImpl(BuildingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Building> findByDescriptionContaining(@NonNull final String subStr) {
        return repository.findByDescriptionContaining(subStr);
    }

    @Override
    public Building save(@NonNull final Building model) {
        return repository.save(model);
    }

    @Override
    public Optional<Building> findById(@NonNull final Long id) {
        return repository.findById(id);
    }
}
