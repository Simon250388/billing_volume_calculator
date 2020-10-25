package com.best.billing.servicebuilder.services.helpers.implementation;

import com.best.billing.servicebuilder.dto.helpers.KeyRoomDetailPropertyDTO;
import com.best.billing.servicebuilder.models.historychange.RoomOwner;
import com.best.billing.servicebuilder.models.historychange.RoomPrescribed;
import com.best.billing.servicebuilder.models.historychange.RoomResident;
import com.best.billing.servicebuilder.models.historychange.RoomSquare;
import com.best.billing.servicebuilder.repositories.historychange.RoomOwnerRepository;
import com.best.billing.servicebuilder.repositories.historychange.RoomPrescribedRepository;
import com.best.billing.servicebuilder.repositories.historychange.RoomResidentRepository;
import com.best.billing.servicebuilder.repositories.historychange.RoomSquareRepository;
import com.best.billing.servicebuilder.services.helpers.KeyRoomDetailProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
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
    public KeyRoomDetailPropertyDTO doGetLastDetails(Long keyRoomId) {
        CompletableFuture<Optional<RoomOwner>> roomOwnerLastRow = ownerRepository.findOneLastByKeyRoomIdAsync(keyRoomId);
        CompletableFuture<Optional<RoomPrescribed>> prescribedLastRow = prescribedRepository.findOneLastByKeyRoomIdAsync(keyRoomId);
        CompletableFuture<Optional<RoomResident>> residentLastRow = residentRepository.findOneLastByKeyRoomIdAsync(keyRoomId);
        CompletableFuture<Optional<RoomSquare>> commonSquareLastRow = squareRepository.findOneLastCommonSquareByKeyRoomIdAsync(keyRoomId);

        CompletableFuture.allOf(roomOwnerLastRow, prescribedLastRow, residentLastRow, commonSquareLastRow).join();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);

        return KeyRoomDetailPropertyDTO.builder()
                .keyRoomId(keyRoomId)
                .ownerCount(roomOwnerLastRow.join().map(RoomOwner::getOwnerCount).orElse(0))
                .prescribedCount(prescribedLastRow.join().map(RoomPrescribed::getPrescribedCount).orElse(0))
                .residentCount(residentLastRow.join().map(RoomResident::getResidentCount).orElse(0))
                .commonSquare(commonSquareLastRow.join().map(RoomSquare::getValue).orElse(0))
                .lsStateAt(calendar.getTime())
                .lsIsActive(true)
                .address("address")
                .lsNumber("lsNUmber")
                .debt(0)
                .build();
    }
}
