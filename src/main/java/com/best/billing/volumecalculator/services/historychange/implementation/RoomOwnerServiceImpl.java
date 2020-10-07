package com.best.billingvolumecalculator.services.historychange.implementation;

import com.best.billingvolumecalculator.dto.changehistory.RoomOwnerDTO;
import com.best.billingvolumecalculator.mappers.changehistory.RoomOwnerMapper;
import com.best.billingvolumecalculator.models.historychange.RoomOwner;
import com.best.billingvolumecalculator.repositories.historychange.RoomOwnerRepository;
import com.best.billingvolumecalculator.services.historychange.RoomOwnerService;
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
    public Optional<RoomOwner> getLastByKeyRoomId(long keyRoomId) {
        return this.repository.getLastByKeyRoom(keyRoomId);
    }

    @Override
    public RoomOwner save(RoomOwner accountingPoint) {
        return this.repository.save(accountingPoint);
    }

    @Override
    public Optional<RoomOwnerDTO> findById(long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }
}
