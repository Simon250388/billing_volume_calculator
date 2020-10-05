package com.best.billingvolumecalculator.services.entity.implementation;

import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoom;
import com.best.billingvolumecalculator.repositories.entity.AccountingPointKeyRoomRepository;
import com.best.billingvolumecalculator.services.entity.AccountingPointKeyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class AccountingPointKeyRoomServiceImpl implements AccountingPointKeyRoomService {

    private final AccountingPointKeyRoomRepository repository;

    @Autowired
    public AccountingPointKeyRoomServiceImpl(@NotNull AccountingPointKeyRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountingPointKeyRoom save(AccountingPointKeyRoom accountingPoint) {
        return this.repository.save(accountingPoint);
    }

    @Override
    public Optional<AccountingPointKeyRoom> findById(long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<AccountingPointKeyRoom> findByKeyRoomId(long keyRoomId) {
        return this.repository.findAllByKeyRoom(keyRoomId);
    }
}
