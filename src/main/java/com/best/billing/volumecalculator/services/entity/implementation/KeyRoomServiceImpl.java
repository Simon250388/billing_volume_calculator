package com.best.billing.volumecalculator.services.entity.implementation;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.mappers.entity.KeyRoomMapper;
import com.best.billing.volumecalculator.repositories.entity.KeyRoomRepository;
import com.best.billing.volumecalculator.services.entity.KeyRoomService;
import org.jetbrains.annotations.NotNull;
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
    public KeyRoomDTO save(@NotNull final KeyRoomDTO dto) {
        return mapper.fromEntity(this.repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<KeyRoomDTO> findById(@NotNull final Long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<KeyRoomDTO> findAll() {
        return mapper.fromEntity(this.repository.findAll());
    }

    @Override
    public Iterable<KeyRoomDTO> findAll(@NotNull final Long buildingId) {
        return mapper.fromEntity(this.repository.findAllByBuildingId(buildingId));
    }

    @Override
    public Iterable<KeyRoomDTO> findAll(@NotNull final Long buildingId, @NotNull final Long roomId) {
        return mapper.fromEntity(this.repository.findAllByBuildingIdAndRoomId(buildingId, roomId));
    }
}
