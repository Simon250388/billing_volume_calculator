package com.best.billing.volumecalculator.services.entity.implementation;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.mappers.entity.KeyRoomMapper;
import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import com.best.billing.volumecalculator.models.historychange.RoomPrescribed;
import com.best.billing.volumecalculator.repositories.entity.KeyRoomRepository;
import com.best.billing.volumecalculator.repositories.historychange.RoomOwnerRepository;
import com.best.billing.volumecalculator.repositories.historychange.RoomPrescribedRepository;
import com.best.billing.volumecalculator.services.entity.KeyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class KeyRoomServiceImpl implements KeyRoomService {
    private final KeyRoomRepository repository;
    private final KeyRoomMapper mapper;
    private final RoomOwnerRepository roomOwnerRepository;
    private final RoomPrescribedRepository roomPrescribedRepository;

    @Autowired
    public KeyRoomServiceImpl(
            KeyRoomRepository repository,
            KeyRoomMapper mapper, RoomOwnerRepository roomOwnerRepository, RoomPrescribedRepository roomPrescribedRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.roomOwnerRepository = roomOwnerRepository;
        this.roomPrescribedRepository = roomPrescribedRepository;
    }

    @Override
    public KeyRoomDTO save(KeyRoomDTO dto) {
        return mapper.fromEntity(this.repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<KeyRoomDTO> findById(final long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Iterable<KeyRoomDTO> findAll() {
        return mapper.fromEntity(this.repository.findAll());
    }

    @Override
    public Iterable<KeyRoomDTO> findAll(final long buildingId) {
        return mapper.fromEntity(this.repository.findAllByBuildingId(buildingId));
    }

    @Override
    public Iterable<KeyRoomDTO> findAll(final long buildingId, final long roomId) {
        return mapper.fromEntity(this.repository.findAllByBuildingIdAndRoomId(buildingId, roomId));
    }

    @Override
    public Optional<KeyRoomDTO> doGetLastDetails(Long keyRoomId) {
        CompletableFuture<RoomOwner> roomOwnerLastRow = roomOwnerRepository.findOneLastByKeyRoomIdAsync(keyRoomId);
        CompletableFuture<RoomPrescribed> roomPrescribedLastRow = roomPrescribedRepository.findOneLastByKeyRoomIdAsync(keyRoomId);
        CompletableFuture.allOf(roomOwnerLastRow, roomPrescribedLastRow).join();

        return Optional.empty();
    }
}
