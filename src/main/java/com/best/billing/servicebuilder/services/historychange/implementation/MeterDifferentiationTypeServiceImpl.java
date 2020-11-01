package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.MeterDifferentiationTypeDTO;
import com.best.billing.servicebuilder.mappers.historychange.MeterDifferentiationTypeMapper;
import com.best.billing.servicebuilder.repository.historychange.MeterDifferentiationTypeRepository;
import com.best.billing.servicebuilder.services.historychange.MeterDifferentiationTypeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeterDifferentiationTypeServiceImpl implements MeterDifferentiationTypeService {
    private final MeterDifferentiationTypeRepository repository;
    private final MeterDifferentiationTypeMapper mapper;

    @Autowired
    public MeterDifferentiationTypeServiceImpl(MeterDifferentiationTypeRepository repository, MeterDifferentiationTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<MeterDifferentiationTypeDTO> doGetLastByKeyRoomId(@NotNull Long keyRoomId) {
        return mapper.fromEntity(repository.findAllLastByKeyRoomId(keyRoomId));
    }

    @Override
    public MeterDifferentiationTypeDTO save(@NotNull MeterDifferentiationTypeDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<MeterDifferentiationTypeDTO> findById(@NotNull Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
