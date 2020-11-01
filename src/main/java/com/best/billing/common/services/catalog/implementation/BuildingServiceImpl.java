package com.best.billing.common.services.catalog.implementation;

import com.best.billing.common.dto.BuildingDTO;
import com.best.billing.common.repository.catalog.BuildingRepository;
import com.best.billing.common.services.catalog.BuildingService;
import com.best.billing.common.mappers.catalog.BuildingMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository repository;
    private final BuildingMapper mapper;

    @Autowired
    public BuildingServiceImpl(BuildingRepository repository, BuildingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<BuildingDTO> findByDescriptionContaining(@NotNull final String subStr) {
        return mapper.fromEntity(repository.findByDescriptionContaining(subStr));
    }

    @Override
    public BuildingDTO save(@NotNull final BuildingDTO dto) {

        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<BuildingDTO> findById(@NotNull final Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
