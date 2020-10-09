package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.RoomOwnerDTO;
import com.best.billing.volumecalculator.mappers.historychange.RoomOwnerMapper;
import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import com.best.billing.volumecalculator.repositories.historychange.RoomOwnerRepository;
import com.best.billing.volumecalculator.services.historychange.RoomOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
    public Optional<RoomOwnerDTO> getLastByKeyRoomId(final long keyRoomId) {
        return this.repository.getLastByKeyRoom(keyRoomId).map(mapper::fromEntity);
    }

    @Override
    public RoomOwnerDTO save(RoomOwner accountingPoint) {
        return mapper.fromEntity(this.repository.save(accountingPoint));
    }

    @Override
    public Optional<RoomOwnerDTO> findById(final long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<RoomOwnerDTO> doGetHistoryByKeyRoomId(final long keyRoomId) {
        return mapper.fromEntity(this.repository.findAllByKeyRoomId(keyRoomId));
    }
}
