package com.best.billing.volumecalculator.services.helpers.implementation;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import com.best.billing.volumecalculator.models.historychange.RoomPrescribed;
import com.best.billing.volumecalculator.models.historychange.RoomResident;
import com.best.billing.volumecalculator.models.historychange.RoomSquare;
import com.best.billing.volumecalculator.repositories.historychange.RoomOwnerRepository;
import com.best.billing.volumecalculator.repositories.historychange.RoomPrescribedRepository;
import com.best.billing.volumecalculator.repositories.historychange.RoomResidentRepository;
import com.best.billing.volumecalculator.repositories.historychange.RoomSquareRepository;
import com.best.billing.volumecalculator.services.helpers.KeyRoomDetailProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class KeyRoomDetailPropertyImpl implements KeyRoomDetailProperty {
    private final RoomOwnerRepository ownerRepository;
    private final RoomPrescribedRepository prescribedRepository;
    private final RoomResidentRepository residentRepository;
    private final RoomSquareRepository squareRepository;

    @Autowired
    public KeyRoomDetailPropertyImpl(
            RoomOwnerRepository ownerRepository,
            RoomPrescribedRepository prescribedRepository,
            RoomResidentRepository residentRepository,
            RoomSquareRepository squareRepository) {
        this.ownerRepository = ownerRepository;
        this.prescribedRepository = prescribedRepository;
        this.residentRepository = residentRepository;
        this.squareRepository = squareRepository;
    }

    @Override
    public Optional<KeyRoomDTO> doGetLastDetails(Long keyRoomId) {
        CompletableFuture<RoomOwner> roomOwnerLastRow = ownerRepository.findOneLastByKeyRoomIdAsync(keyRoomId);
        CompletableFuture<RoomPrescribed> prescribedLastRow = prescribedRepository.findOneLastByKeyRoomIdAsync(keyRoomId);
        CompletableFuture<RoomResident> residentLastRow = residentRepository.findOneLastByKeyRoomIdAsync(keyRoomId);
        CompletableFuture<RoomSquare> commonSquareLastRow = squareRepository.findOneLastCommonSquareByKeyRoomIdAsync(keyRoomId);
        CompletableFuture.allOf(roomOwnerLastRow, prescribedLastRow, residentLastRow, commonSquareLastRow).join();
        return Optional.empty();
    }
}
