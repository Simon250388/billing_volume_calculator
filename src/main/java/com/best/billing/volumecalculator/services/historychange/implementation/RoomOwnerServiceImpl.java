package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import com.best.billing.volumecalculator.repositories.historychange.RoomOwnerRepository;
import com.best.billing.volumecalculator.services.historychange.RoomOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomOwnerServiceImpl implements RoomOwnerService {
    private final RoomOwnerRepository repository;

    @Autowired
    public RoomOwnerServiceImpl(RoomOwnerRepository repository) {
        this.repository = repository;
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
    public Optional<RoomOwner> findById(long id) {
        return this.repository.findById(id);
    }
}
