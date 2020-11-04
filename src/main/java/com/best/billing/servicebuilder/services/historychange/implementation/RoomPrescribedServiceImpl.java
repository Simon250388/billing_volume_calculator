package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.RoomPrescribedDTO;
import com.best.billing.servicebuilder.mappers.historychange.RoomPrescribedMapper;
import com.best.billing.servicebuilder.repository.historychange.RoomPrescribedRepository;
import com.best.billing.servicebuilder.services.historychange.RoomPrescribedService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoomPrescribedServiceImpl implements RoomPrescribedService {
    private final RoomPrescribedRepository repository;
    private final RoomPrescribedMapper mapper;

    @Autowired
    public RoomPrescribedServiceImpl(RoomPrescribedRepository repository, RoomPrescribedMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<RoomPrescribedDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId) {
        return repository.findOneLastByKeyRoomId(keyRoomId).map(mapper::fromEntity);
    }

    @Override
    public Iterable<RoomPrescribedDTO> doGetHistoryByKeyRoomId(@NonNull final Long keyRoomId) {
        return mapper.fromEntity(repository.findAllByKeyRoomId(keyRoomId));
    }

    @Override
    public RoomPrescribedDTO save(@NonNull final RoomPrescribedDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<RoomPrescribedDTO> findById(@NonNull final Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
