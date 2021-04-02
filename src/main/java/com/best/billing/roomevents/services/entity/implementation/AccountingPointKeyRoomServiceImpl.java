package com.best.billing.roomevents.services.entity.implementation;

import com.best.billing.roomevents.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billing.roomevents.mappers.entity.AccountingPointKeyRoomMapper;
import com.best.billing.roomevents.repository.entity.AccountingPointKeyRoomRepository;
import com.best.billing.roomevents.services.entity.AccountingPointKeyRoomServiceService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountingPointKeyRoomServiceImpl implements AccountingPointKeyRoomServiceService {

    private final AccountingPointKeyRoomRepository repository;
    private final AccountingPointKeyRoomMapper mapper;

    @Autowired
    public AccountingPointKeyRoomServiceImpl(AccountingPointKeyRoomRepository repository, AccountingPointKeyRoomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointKeyRoomDTO save(@NonNull final AccountingPointKeyRoomDTO dto) {
        return mapper.fromEntity(this.repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointKeyRoomDTO> findById(@NonNull final Long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public List<AccountingPointKeyRoomDTO> findByKeyRoomId(@NonNull final Long keyRoomId) {
        return this.mapper.fromEntity(this.repository.findAllByKeyRoomId(keyRoomId));
    }
}