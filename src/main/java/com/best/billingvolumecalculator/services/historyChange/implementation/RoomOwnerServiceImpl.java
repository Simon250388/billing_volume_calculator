package com.best.billingvolumecalculator.services.historyChange.implementation;

import com.best.billingvolumecalculator.models.historyChange.RoomOwner;
import com.best.billingvolumecalculator.repositories.historyChange.RoomOwnerRepository;
import com.best.billingvolumecalculator.services.historyChange.RoomOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomOwnerServiceImpl implements RoomOwnerService {
    private RoomOwnerRepository repository;

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
