package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.RoomOwnerDTO;
import com.best.billing.volumecalculator.mappers.historychange.RoomOwnerMapper;
import com.best.billing.volumecalculator.repositories.historychange.RoomOwnerRepository;
import com.best.billing.volumecalculator.services.historychange.RoomOwnerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoomOwnerServiceImpl implements RoomOwnerService {
    private final RoomOwnerRepository repository;
    private final RoomOwnerMapper mapper;

    @Autowired
    public RoomOwnerServiceImpl(
            RoomOwnerRepository repository,
            RoomOwnerMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<RoomOwnerDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId) {
        return this.repository.findOneLastByKeyRoomId(keyRoomId).map(mapper::fromEntity);
    }

    @Override
    public RoomOwnerDTO save(@NotNull RoomOwnerDTO dto) {
        return mapper.fromEntity(this.repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<RoomOwnerDTO> findById(@NotNull final Long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<RoomOwnerDTO> doGetHistoryByKeyRoomId(@NotNull final Long keyRoomId) {
        return mapper.fromEntity(this.repository.findAllByKeyRoomId(keyRoomId));
    }
}
