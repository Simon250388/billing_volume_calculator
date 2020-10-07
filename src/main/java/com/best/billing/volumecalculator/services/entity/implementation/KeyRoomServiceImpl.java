package com.best.billing.volumecalculator.services.entity.implementation;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.mappers.entity.KeyRoomMapper;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.services.entity.KeyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeyRoomServiceImpl implements KeyRoomService {
    private final CrudRepository<KeyRoom, Long> repository;
    private final KeyRoomMapper mapper;

    @Autowired
    public KeyRoomServiceImpl(
            CrudRepository<KeyRoom, Long> repository,
            KeyRoomMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public KeyRoomDTO save(KeyRoom accountingPoint) {
        return mapper.fromEntity(this.repository.save(accountingPoint));
    }

    @Override
    public Optional<KeyRoomDTO> findById(long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }
}
