package com.best.billingvolumecalculator.services.entity.implementation;

import com.best.billingvolumecalculator.models.entity.KeyRoom;
import com.best.billingvolumecalculator.services.entity.KeyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeyRoomServiceImpl implements KeyRoomService {
    private final CrudRepository<KeyRoom, Long> repository;

    @Autowired
    public KeyRoomServiceImpl(CrudRepository<KeyRoom, Long> repository) {
        this.repository = repository;
    }

    @Override
    public KeyRoom save(KeyRoom accountingPoint) {
        return this.repository.save(accountingPoint);
    }

    @Override
    public Optional<KeyRoom> findById(long id) {
        return this.repository.findById(id);
    }
}
