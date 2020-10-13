package com.best.billing.volumecalculator.services.historychange.implementation;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.volumecalculator.mappers.historychange.AccountingPointServiceStateMapper;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointMeterStateRepository;
import com.best.billing.volumecalculator.repositories.historychange.AccountingPointServiceStateRepository;
import com.best.billing.volumecalculator.services.historychange.AccountingPointServiceStateService;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountingPointServiceStateServiceImpl implements AccountingPointServiceStateService {
    private final AccountingPointServiceStateRepository repository;
    private final AccountingPointServiceStateMapper mapper;
    private final AccountingPointMeterStateRepository meterStateRepository;

    public AccountingPointServiceStateServiceImpl(AccountingPointServiceStateRepository repository, AccountingPointServiceStateMapper mapper, AccountingPointMeterStateRepository meterStateRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.meterStateRepository = meterStateRepository;
    }

    @Override
    public Iterable<AccountingPointServiceStateDTO> doGetAllActiveAccountingPointDetailByKeyRoomId(long keyRoomId) {
        Stream<AccountingPointServiceState> activeAccountingPoint = ImmutableList.copyOf(repository.findAllActiveByKeyRoomId(keyRoomId)).stream();
        Stream<AccountingPointMeterState> activeMeters = ImmutableList.copyOf(meterStateRepository.findAllLastByKeyRoomId(keyRoomId)).stream();

        return activeAccountingPoint
                .map(row ->
                        activeMeters
                                .filter(activeMeter -> activeMeter.getAccountingPointKeyRoomServiceEntity() == row.getAccountingPointKeyRoomServiceEntity())
                                .findFirst()
                                .map(activeMeter -> mapper.fromEntityAndMeterState(row, activeMeter))
                                .orElse(mapper.fromEntity(row))
                )
                .collect(Collectors.toList());
    }

    @Override
    public AccountingPointServiceStateDTO save(AccountingPointServiceStateDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointServiceStateDTO> findById(long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
