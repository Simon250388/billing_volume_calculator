package org.billing.accountingpoints.service.implementation;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.billing.accountingpoints.mapper.ModelMapper;
import org.billing.accountingpoints.model.AccountingPointServiceState;
import org.billing.accountingpoints.repository.AccountingPointServiceStateRepository;
import org.billing.accountingpoints.service.ServiceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ServiceStateServiceImpl implements ServiceStateService {

  private final AccountingPointServiceStateRepository accountingPointServiceStateDao;
  private final ModelMapper<AccountingPointServiceState, AccountingPointServiceStateDto> mapper;

  @Override
  public Set<AccountingPointServiceStateDto> currentActive(@NonNull UUID keyRoomId) {
    return Collections.emptySet();
  }

  @Override
  public Set<AccountingPointServiceStateDto> currentActive(@NonNull UUID keyRoomId, @NonNull Instant period) {
    return Collections.emptySet();
  }

  @Override
  public Set<AccountingPointServiceStateDto> currentActive(
          @NonNull UUID keyRoomId, @NonNull Instant period, @NonNull Instant periodFact) {
    return Collections.emptySet();
  }

  @Override
  public CompletableFuture<Set<AccountingPointServiceStateDto>> currentActiveByEntityServiceIdAsync(
      Set<UUID> allEntityServiceId) {
    return null;
  }

  @Override
  public AccountingPointServiceStateDto changeState(AccountingPointServiceStateDto dto) {
    if (dto.isActive()) return disconnect(dto);
    else return connect(dto);
  }

  @Override
  public Collection<AccountingPointServiceStateDto> getHistory(@NonNull UUID keyRoomId) {
    return mapper.toDto(accountingPointServiceStateDao.findAll(keyRoomId));
  }

  @Override
  public Collection<AccountingPointServiceStateDto> getHistory(@NonNull UUID keyRoomId, @NonNull Instant from) {
    return mapper.toDto(accountingPointServiceStateDao.findAll(keyRoomId));
  }

  @Override
  public Collection<AccountingPointServiceStateDto> getHistory(@NonNull UUID keyRoomId, @NonNull Instant from, @NonNull Instant to) {
    return mapper.toDto(accountingPointServiceStateDao.findAll(keyRoomId));
  }

  private AccountingPointServiceStateDto connect(AccountingPointServiceStateDto dto) {
    if (hasAccountingPointActiveService(dto.getKeyRoomID(), dto.getAccountPointId())) {
      throw new IllegalArgumentException("");
    }
    return mapper.toDto(accountingPointServiceStateDao.save(mapper.toModel(dto)));
  }

  private AccountingPointServiceStateDto disconnect(AccountingPointServiceStateDto dto) {
    if (!hasAccountingPointActiveService(dto.getKeyRoomID(), dto.getAccountPointId())) {
      throw new IllegalArgumentException("");
    }
    return mapper.toDto(accountingPointServiceStateDao.save(mapper.toModel(dto)));
  }

  private boolean hasAccountingPointActiveService(UUID keyRoomId, UUID accountingPointId) {
    return Optional.ofNullable(
            accountingPointServiceStateDao.findAllActive(keyRoomId, accountingPointId))
        .isPresent();
  }
}
