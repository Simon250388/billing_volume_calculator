package com.best.billing.stabs;

import com.best.billing.common.model.Building;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import com.best.billing.common.model.enums.MeterState;
import com.best.billing.common.model.enums.SquareType;
import com.best.billing.common.model.AccountingPoint;
import com.best.billing.common.model.Meter;
import com.best.billing.roomevents.models.AccountingPointKeyRoom;
import com.best.billing.roomevents.models.AccountingPointKeyRoomServiceEntity;
import com.best.billing.common.model.KeyRoom;
import com.best.billing.roomevents.models.AccountingPointMeterState;
import com.best.billing.roomevents.models.AccountingPointServiceState;
import com.best.billing.roomevents.models.RoomSquare;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class StabFactory {

    public final Service service = Service.builder().description("Service").build();

    public final Meter meter = Meter.builder().description("Meter").build();

    public final Building building = Building.builder().description("Building").build();

    public final KeyRoom keyRoomPrivateSector = KeyRoom.builder().building(building).room(null).privateSector(true).build();

    public final AccountingPoint accountingPoint = AccountingPoint.builder().description("AccountingPoint").build();

    public final AccountingPointKeyRoom accountingPointKeyRoom = AccountingPointKeyRoom.builder().keyRoom(keyRoomPrivateSector).accountingPoint(accountingPoint).build();

    public final DirectionOfUse directionOfUse = DirectionOfUse.builder().description("directionOfUse").build();

    public final AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity = AccountingPointKeyRoomServiceEntity.builder()
            .accountingPointKeyRoom(accountingPointKeyRoom)
            .service(service)
            .directionOfUse(directionOfUse)
            .build();

    public final AccountingPointServiceState accountingPointServiceStateActive = AccountingPointServiceState.builder()
            .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
            .active(true)
            .period(LocalDateTime.now())
            .build();

    public final AccountingPointServiceState accountingPointServiceStateNotActive = AccountingPointServiceState.builder()
            .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
            .active(false)
            .period(LocalDateTime.now())
            .build();

    public RoomSquare commonRoomSquare(LocalDateTime period, int value) {
        return RoomSquare.builder()
                .keyRoom(keyRoomPrivateSector)
                .period(period)
                .squareType(SquareType.COMMON)
                .value(value)
                .build();
    }

    public AccountingPointMeterState AccountingPointMeterState(MeterState meterState) {
        return AccountingPointMeterState.builder()
                .meter(meter)
                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                .meterState(meterState)
                .build();
    }

    public AccountingPointServiceState AccountingPointServiceState(boolean active) {
        return AccountingPointServiceState
                .builder()
                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                .active(active)
                .build();
    }
}

