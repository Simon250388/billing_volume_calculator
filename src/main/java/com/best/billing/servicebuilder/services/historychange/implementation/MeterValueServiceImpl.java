package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.MeterValueDTO;
import com.best.billing.servicebuilder.mappers.historychange.MeterValueMapper;
import com.best.billing.servicebuilder.repositories.historychange.MeterValueRepository;
import com.best.billing.servicebuilder.services.historychange.MeterValueService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MeterValueServiceImpl implements MeterValueService {
    private  final MeterValueRepository repository;
    private final MeterValueMapper mapper;

    @Autowired
    public MeterValueServiceImpl(MeterValueRepository repository, MeterValueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<MeterValueDTO> doGetLastByKeyRoomId(@NotNull Long keyRoomId) {
        return mapper.fromEntity(repository.findAllLastByKeyRoomId(keyRoomId));
    }

    @Override
    public MeterValueDTO save(@NotNull MeterValueDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<MeterValueDTO> findById(@NotNull Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
