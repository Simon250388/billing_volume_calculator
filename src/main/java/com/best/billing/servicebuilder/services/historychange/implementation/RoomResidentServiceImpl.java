package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.RoomResidentdDTO;
import com.best.billing.servicebuilder.mappers.historychange.RoomResidentMapper;
import com.best.billing.servicebuilder.repository.historychange.RoomResidentRepository;
import com.best.billing.servicebuilder.services.historychange.RoomResidentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomResidentServiceImpl implements RoomResidentService {
    private final RoomResidentMapper mapper;
    private final RoomResidentRepository repository;

    @Autowired
    public RoomResidentServiceImpl(RoomResidentMapper mapper, RoomResidentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Iterable<RoomResidentdDTO> doGetHistoryByKeyRoomId(@NotNull final Long keyRoomId) {
        return mapper.fromEntity(repository.findAllByKeyRoomId(keyRoomId));
    }

    @Override
    public Optional<RoomResidentdDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId) {
        return repository.findOneLastByKeyRoomId(keyRoomId).map(mapper::fromEntity);
    }

    @Override
    public RoomResidentdDTO save(@NotNull final RoomResidentdDTO entity) {
        return mapper.fromEntity(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public Optional<RoomResidentdDTO> findById(@NotNull final Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
