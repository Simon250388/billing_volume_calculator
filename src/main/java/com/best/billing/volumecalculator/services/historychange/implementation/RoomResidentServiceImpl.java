package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.RoomResidentdDTO;
import com.best.billing.volumecalculator.mappers.historychange.RoomResidentMapper;
import com.best.billing.volumecalculator.repositories.historychange.RoomResidentRepository;
import com.best.billing.volumecalculator.services.historychange.RoomResidentService;
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
    public Iterable<RoomResidentdDTO> doGetHistoryByKeyRoomId(long keyRoomId) {
        return mapper.fromEntity(repository.findAllByKeyRoom_Id(keyRoomId));
    }

    @Override
    public Optional<RoomResidentdDTO> doGetLastByKeyRoomId(long keyRoomId) {
        return repository.findOneLastByKeyRoomId(keyRoomId).map(mapper::fromEntity);
    }

    @Override
    public RoomResidentdDTO save(RoomResidentdDTO entity) {
        return mapper.fromEntity(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public Optional<RoomResidentdDTO> findById(long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
