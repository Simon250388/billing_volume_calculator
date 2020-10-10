package com.best.billing.volumecalculator.services.catalog.implementation;

import com.best.billing.volumecalculator.dto.catalog.BuildingDTO;
import com.best.billing.volumecalculator.mappers.catalog.BuildingMapper;
import com.best.billing.volumecalculator.models.catalog.Building;
import com.best.billing.volumecalculator.repositories.catalog.BuildingRepository;
import com.best.billing.volumecalculator.services.catalog.BuildingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository repository;
    private final BuildingMapper mapper;

    public BuildingServiceImpl(BuildingRepository repository, BuildingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<BuildingDTO> findByDescriptionContaining(String subStr) {
        return mapper.fromEntity(repository.findByDescriptionContaining(subStr));
    }

    @Override
    public BuildingDTO save(Building accountingPoint) {
        return mapper.fromEntity(repository.save(accountingPoint));
    }

    @Override
    public Optional<BuildingDTO> findById(long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
