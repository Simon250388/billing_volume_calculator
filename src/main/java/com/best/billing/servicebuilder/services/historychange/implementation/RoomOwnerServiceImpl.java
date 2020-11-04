package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.RoomOwnerDTO;
import com.best.billing.servicebuilder.mappers.historychange.RoomOwnerMapper;
import com.best.billing.servicebuilder.repository.historychange.RoomOwnerRepository;
import com.best.billing.servicebuilder.services.historychange.RoomOwnerService;
import lombok.NonNull;
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
    public Optional<RoomOwnerDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId) {
        return this.repository.findOneLastByKeyRoomId(keyRoomId).map(mapper::fromEntity);
    }

    @Override
    public RoomOwnerDTO save(@NonNull RoomOwnerDTO dto) {
        return mapper.fromEntity(this.repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<RoomOwnerDTO> findById(@NonNull final Long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<RoomOwnerDTO> doGetHistoryByKeyRoomId(@NonNull final Long keyRoomId) {
        return mapper.fromEntity(this.repository.findAllByKeyRoomId(keyRoomId));
    }
}
