package com.best.billing.roomevents.services.historychange.implementation;

import com.best.billing.roomevents.dto.historychange.MeterDifferentiationTypeDTO;
import com.best.billing.roomevents.mappers.historychange.MeterDifferentiationTypeMapper;
import com.best.billing.roomevents.repository.historychange.MeterDifferentiationTypeRepository;
import com.best.billing.roomevents.services.historychange.MeterDifferentiationTypeService;
import lombok.NonNull;
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
    public Iterable<MeterDifferentiationTypeDTO> doGetLastByKeyRoomId(@NonNull Long keyRoomId) {
        return mapper.fromEntity(repository.findAllLastByKeyRoomId(keyRoomId));
    }

    @Override
    public MeterDifferentiationTypeDTO save(@NonNull MeterDifferentiationTypeDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<MeterDifferentiationTypeDTO> findById(@NonNull Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
