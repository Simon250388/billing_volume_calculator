package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.RoomPrescribedDTO;
import com.best.billing.volumecalculator.mappers.historychange.RoomPrescribedMapper;
import com.best.billing.volumecalculator.repositories.historychange.RoomPrescribedRepository;
import com.best.billing.volumecalculator.services.historychange.RoomPrescribedService;
import org.jetbrains.annotations.NotNull;
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
    public Optional<RoomPrescribedDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId) {
        return repository.findOneLastByKeyRoomId(keyRoomId).map(mapper::fromEntity);
    }

    @Override
    public Iterable<RoomPrescribedDTO> doGetHistoryByKeyRoomId(@NotNull final Long keyRoomId) {
        return mapper.fromEntity(repository.findAllByKeyRoomId(keyRoomId));
    }

    @Override
    public RoomPrescribedDTO save(@NotNull final RoomPrescribedDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<RoomPrescribedDTO> findById(@NotNull final Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
