package com.best.billing.servicebuilder.services.helpers.implementation;

import com.best.billing.servicebuilder.dto.helpers.KeyRoomDetailPropertyDTO;
import com.best.billing.servicebuilder.models.historychange.RoomOwner;
import com.best.billing.servicebuilder.models.historychange.RoomPrescribed;
import com.best.billing.servicebuilder.models.historychange.RoomResident;
import com.best.billing.servicebuilder.models.historychange.RoomSquare;
import com.best.billing.servicebuilder.repository.historychange.RoomOwnerRepository;
import com.best.billing.servicebuilder.repository.historychange.RoomPrescribedRepository;
import com.best.billing.servicebuilder.repository.historychange.RoomResidentRepository;
import com.best.billing.servicebuilder.repository.historychange.RoomSquareRepository;
import com.best.billing.servicebuilder.services.helpers.KeyRoomDetailProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

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
        Optional<RoomOwner> roomOwnerLastRow = ownerRepository.findOneLastByKeyRoomId(keyRoomId);
        Optional<RoomPrescribed> prescribedLastRow = prescribedRepository.findOneLastByKeyRoomId(keyRoomId);
        Optional<RoomResident> residentLastRow = residentRepository.findOneLastByKeyRoomId(keyRoomId);
        Optional<RoomSquare> commonSquareLastRow = squareRepository.findOneLastCommonSquareByKeyRoomId(keyRoomId);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);

        return KeyRoomDetailPropertyDTO.builder()
                .keyRoomId(keyRoomId)
                .ownerCount(roomOwnerLastRow.orElse(new RoomOwner()).getOwnerCount())
                .prescribedCount(prescribedLastRow.orElse(new RoomPrescribed()).getPrescribedCount())
                .residentCount(residentLastRow.orElse(new RoomResident()).getResidentCount())
                .commonSquare(commonSquareLastRow.orElse(new RoomSquare()).getValue())
                .lsStateAt(calendar.getTime())
                .lsIsActive(true)
                .address("address")
                .lsNumber("lsNUmber")
                .debt(0)
                .build();
    }
}
