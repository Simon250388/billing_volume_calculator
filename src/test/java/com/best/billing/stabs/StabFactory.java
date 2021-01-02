package com.best.billing.stabs;

import com.best.billing.common.model.Building;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import com.best.billing.common.model.enums.SquareType;
import com.best.billing.servicebuilder.models.catalog.AccountingPoint;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoom;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceState;
import com.best.billing.servicebuilder.models.historychange.RoomSquare;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Builder
public class StabFactory {
    public final Service service = Service.builder().description("Service").build();
    public final Building building = Building.builder().description("Building").build();
    public final KeyRoom keyRoom = KeyRoom.builder().building(building).room(null).privateSector(true).build();
    public final AccountingPoint accountingPoint = AccountingPoint.builder().description("AccountingPoint").build();
    public final AccountingPointKeyRoom accountingPointKeyRoom = AccountingPointKeyRoom.builder().keyRoom(keyRoom).accountingPoint(accountingPoint).build();
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

    public final RoomSquare roomSquare30 = RoomSquare.builder()
            .keyRoom(keyRoom)
            .period(LocalDateTime.of(2019, 1, 1, 0, 0, 0))
            .squareType(SquareType.common)
            .value(30)
            .build();

    public final RoomSquare roomSquare20 = RoomSquare.builder()
            .keyRoom(keyRoom)
            .period(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
            .squareType(SquareType.common)
            .value(20)
            .build();
}

