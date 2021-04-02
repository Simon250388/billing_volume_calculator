package com.best.billing.roomevents.services.helpers.implementation;

import com.best.billing.roomevents.dto.helpers.KeyRoomDetailPropertyDTO;
import com.best.billing.roomevents.models.RoomOwner;
import com.best.billing.roomevents.models.RoomPrescribed;
import com.best.billing.roomevents.models.RoomResident;
import com.best.billing.roomevents.models.RoomSquare;
import com.best.billing.roomevents.repository.historychange.RoomOwnerRepository;
import com.best.billing.roomevents.repository.historychange.RoomPrescribedRepository;
import com.best.billing.roomevents.repository.historychange.RoomResidentRepository;
import com.best.billing.roomevents.repository.historychange.RoomSquareRepository;
import com.best.billing.roomevents.services.helpers.KeyRoomDetailProperty;
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
                .ownerCount(roomOwnerLastRow.orElse(RoomOwner.builder().build()).getOwnerCount())
                .prescribedCount(prescribedLastRow.orElse(RoomPrescribed.builder().build()).getPrescribedCount())
                .residentCount(residentLastRow.orElse(RoomResident.builder().build()).getResidentCount())
                .commonSquare(commonSquareLastRow.orElse(RoomSquare.builder().build()).getValue())
                .lsStateAt(calendar.getTime())
                .lsIsActive(true)
                .address("address")
                .lsNumber("lsNUmber")
                .debt(0)
                .build();
    }
}
