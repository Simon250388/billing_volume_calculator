package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.MeterValueDTO;
import com.best.billing.servicebuilder.mappers.historychange.MeterValueMapper;
import com.best.billing.servicebuilder.repository.historychange.MeterValueRepository;
import com.best.billing.servicebuilder.services.historychange.MeterValueService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeterValueServiceImpl implements MeterValueService {
    private final MeterValueRepository repository;
    private final MeterValueMapper mapper;

    @Autowired
    public MeterValueServiceImpl(MeterValueRepository repository, MeterValueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<MeterValueDTO> doGetLastByKeyRoomId(@NonNull Long keyRoomId) {
        return mapper.fromEntity(repository.findAllLastByKeyRoomId(keyRoomId));
    }

    @Override
    public MeterValueDTO save(@NonNull MeterValueDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<MeterValueDTO> findById(@NonNull Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
