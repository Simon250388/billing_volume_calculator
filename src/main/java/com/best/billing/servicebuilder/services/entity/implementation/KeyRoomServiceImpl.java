package com.best.billing.servicebuilder.services.entity.implementation;

import com.best.billing.servicebuilder.dto.entity.KeyRoomDTO;
import com.best.billing.servicebuilder.mappers.entity.KeyRoomMapper;
import com.best.billing.servicebuilder.repository.entity.KeyRoomRepository;
import com.best.billing.servicebuilder.services.entity.KeyRoomService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class KeyRoomServiceImpl implements KeyRoomService {
    private final KeyRoomRepository repository;
    private final KeyRoomMapper mapper;

    @Autowired
    public KeyRoomServiceImpl(KeyRoomRepository repository, KeyRoomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public KeyRoomDTO save(@NonNull final KeyRoomDTO dto) {
        return mapper.fromEntity(this.repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<KeyRoomDTO> findById(@NonNull final Long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<KeyRoomDTO> findAll() {
        return mapper.fromEntity(this.repository.findAll());
    }

    @Override
    public Iterable<KeyRoomDTO> findAll(@NonNull final Long buildingId) {
        return mapper.fromEntity(this.repository.findAllByBuildingId(buildingId));
    }

    @Override
    public Iterable<KeyRoomDTO> findAll(@NonNull final Long buildingId, @NonNull final Long roomId) {
        return mapper.fromEntity(this.repository.findAllByBuildingIdAndRoomId(buildingId, roomId));
    }
}
