package com.best.billing.volumecalculator.helpers;

import com.best.billing.common.model.*;
import com.best.billing.common.model.enums.MeterState;
import com.best.billing.commonsettings.model.SeasonalitySetting;
import com.best.billing.servicebuilder.models.catalog.AccountingPoint;
import com.best.billing.servicebuilder.models.catalog.Meter;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoom;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceProvider;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceState;
import com.best.billing.volumecalculator.model.StabPeriod;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CalculatorImplTest {

    private CalculationItemBuilder calculationItemBuilder;

    @Test
    void calculate() {

        CalculationItemBuilder calculationItemBuilder = Mockito.mock(CalculationItemBuilder.class);

        when(calculationItemBuilder.buildStream(any())).thenReturn(buildFakeStream());

        Calendar calendar = Calendar.getInstance();

        Calculator calculator = new CalculatorImpl(calculationItemBuilder);

        assertThat(calculator.calculate(calendar.getTime()));

    }

    private Stream<CalculationItem> buildFakeStream() {
        long buildingId = new Random().nextLong();
        Calendar calendar = Calendar.getInstance();

        AccountingPointKeyRoom accountingPointKeyRoom = AccountingPointKeyRoom.builder()
                .accountingPoint(
                        AccountingPoint.builder()
                                .id(new Random().nextLong())
                                .description("огород_Холодное водоснабжение")
                                .build())
                .keyRoom(
                        KeyRoom.builder()
                                .building(
                                        Building.builder()
                                                .id(buildingId)
                                                .description("Плотниково п, Юбилейная ул, Дом № 10")
                                                .build()
                                )
                                .room(
                                        Room.builder()
                                                .id(new Random().nextLong())
                                                .building(
                                                        Building.builder()
                                                                .id(buildingId)
                                                                .build()
                                                )
                                                .description("2")
                                                .build()
                                )
                                .privateSector(false)
                                .build())
                .build();

        DirectionOfUse directionOfUse = DirectionOfUse.builder()
                .description("полив из водопровода")
                .build();

        Service service = Service.builder()
                .id(new Random().nextLong())
                .description("Холодное водоснабжение")
                .build();

        AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity = AccountingPointKeyRoomServiceEntity.builder()
                .accountingPointKeyRoom(accountingPointKeyRoom)
                .service(service)
                .directionOfUse(directionOfUse)
                .build();

        Provider provider = Provider.builder()
                .description("")
                .id(new Random().nextLong())
                .description("ООО ПКС")
                .build();

        List<CalculationItem> fakeList = new ArrayList<CalculationItem>();
        fakeList.add(
                CalculationItem.builder()
                        .stabPeriod(
                                StabPeriod.builder()
                                        .id(new Random().nextLong())
                                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                                        .accountingPointServiceState(
                                                AccountingPointServiceState.builder()
                                                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                                                        .id(new Random().nextLong())
                                                        .active(true)
                                                        .period(LocalDateTime.now())
                                                        .build()
                                        )
                                        .accountingPointMeterState(
                                                AccountingPointMeterState.builder()
                                                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                                                        .id(new Random().nextLong())
                                                        .meter(
                                                                Meter.builder()
                                                                        .id(new Random().nextLong())
                                                                        .description("3")
                                                                        .build()
                                                        )
                                                        .meterState(MeterState.ACTIVE_STATE)
                                                        .period(LocalDateTime.now())
                                                        .build())
                                        .accountingPointServiceProvider(
                                                AccountingPointServiceProvider.builder()
                                                        .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                                                        .id(new Random().nextLong())
                                                        .period(LocalDateTime.now())
                                                        .provider(provider)
                                                        .build())
                                        .registrationPeriod(LocalDate.of(2020, 7, 1))
                                        .build()
                        )
                        .seasonalitySetting(
                                SeasonalitySetting.builder()
                                        .volumeByLastYear(false)
                                        .doNotUseSeasonality(false)
                                        .coefficientNormValue(1)
                                        .coefficientNormValueDoNotUseSeasonality(1)
                                        .build()
                        )
                        .isMeterValueProvide(false)
                        .isSeasonalityActive(true)
                        .build());

        return fakeList.stream();
    }

    private Date dateFromCalendar(Calendar calendar, int year, int month, int day) {
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
