package com.best.billingvolumecalculator.services.entity.implementation;

import com.best.billingvolumecalculator.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billingvolumecalculator.mappers.entity.AccountingPointKeyRoomMapper;
import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoom;
import com.best.billingvolumecalculator.repositories.entity.AccountingPointKeyRoomRepository;
import com.best.billingvolumecalculator.services.entity.AccountingPointKeyRoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountingPointKeyRoomServiceImpl implements AccountingPointKeyRoomService {

    private final AccountingPointKeyRoomRepository repository;
    private final AccountingPointKeyRoomMapper mapper;

    public AccountingPointKeyRoomServiceImpl(AccountingPointKeyRoomRepository repository, AccountingPointKeyRoomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointKeyRoom save(AccountingPointKeyRoom accountingPoint) {
        return this.repository.save(accountingPoint);
    }

    @Override
    public Optional<AccountingPointKeyRoomDTO> findById(long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public List<AccountingPointKeyRoomDTO> findByKeyRoomId(long keyRoomId) {
        return this.mapper.fromEntity(this.repository.findAllByKeyRoom(keyRoomId));
    }
}
