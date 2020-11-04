package com.best.billing.volumecalculator.service;

import com.best.billing.common.model.Service;
import com.best.billing.commonsettings.model.CalculationMethodByDirectionOfUse;
import com.best.billing.commonsettings.model.KeyNormValue;
import com.best.billing.commonsettings.model.RateValue;
import com.best.billing.commonsettings.model.SeasonalitySetting;
import com.best.billing.commonsettings.repository.*;
import com.best.billing.servicebuilder.models.catalog.AccountingPoint;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoom;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.model.AccountingPointServiceAvgVolume;
import com.best.billing.volumecalculator.model.StabPeriod;
import com.best.billing.volumecalculator.repository.AccountingPointServiceAvgVolumeRepository;
import com.best.billing.volumecalculator.repository.StabPeriodRepository;
import com.google.common.collect.ImmutableList;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.charset.Charset;
import java.util.*;

@SpringBootTest
class StabPeriodServiceTest {

    @MockBean
    private StabPeriodRepository repository;
    @MockBean
    private PeriodSeasonalityRepository periodSeasonalityRepository;
    @MockBean
    private CalculationMethodByDirectionOfUseRepository calculationMethodByDirectionOfUseRepository;
    @MockBean
    private SeasonalitySettingsRepository seasonalitySettingsRepository;
    @MockBean
    private AccountingPointServiceAvgVolumeRepository accountingPointServiceAvgVolumeRepository;
    @MockBean
    private RateValueRepository rateValueRepository;
    @MockBean
    private KeyNormValueRepository keyNormValueRepository;

    @Test
    void calculateVolume() {
        long dayOfMonth = 31;
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 10, 1);
        Date calculationPeriod = calendar.getTime();

//        CompletableFuture<Iterable<PeriodSeasonality>> periodSeasonalityCompletableFuture = periodSeasonalityRepository.findAllLastByPeriodAsync(calculationPeriod);
//        CompletableFuture<Iterable<CalculationMethodByDirectionOfUse>> calculationMethodByDirectionOfUseCompletableFuture = calculationMethodByDirectionOfUseRepository.findAllLastByPeriodAsync(calculationPeriod);
//        CompletableFuture<Iterable<SeasonalitySetting>> seasonalitySettingsSeasonalitySettingCompletableFuture = seasonalitySettingsRepository.findAllLastByPeriodAsync(calculationPeriod);
//        CompletableFuture<Iterable<AccountingPointServiceAvgVolume>> accountingPointServiceAvgVolumesIterableCompletableFuture = accountingPointServiceAvgVolumeRepository.findAllLastByPeriodAsync(calculationPeriod);
//        CompletableFuture<Iterable<RateValue>> rateValuesIterableCompletableFuture = rateValueRepository.findAllLastByPeriodAsync(calculationPeriod);
//        CompletableFuture<Iterable<KeyNormValue>> keyNormValuesIterableCompletableFuture = keyNormValueRepository.findAllLastByPeriodAsync(calculationPeriod);
//
//        CompletableFuture.allOf(
//                periodSeasonalityCompletableFuture,
//                calculationMethodByDirectionOfUseCompletableFuture,
//                seasonalitySettingsSeasonalitySettingCompletableFuture,
//                seasonalitySettingsSeasonalitySettingCompletableFuture,
//                accountingPointServiceAvgVolumesIterableCompletableFuture,
//                rateValuesIterableCompletableFuture,
//                keyNormValuesIterableCompletableFuture).join();



        List<StabPeriod> stabPeriodsStream = Collections.singletonList(StabPeriod.builder()
                .id(random.nextLong())
                .accountingPointKeyRoomServiceEntity(
                        AccountingPointKeyRoomServiceEntity.builder()
                                .accountingPointKeyRoom(
                                        AccountingPointKeyRoom.builder()
                                                .accountingPoint(
                                                        AccountingPoint.builder()
                                                                .id(random.nextLong())
                                                                .description(randomString(random))
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()

                )
                .service(
                        Service.builder()
                                .id(random.nextLong())
                                .description(randomString(random))
                                .build()
                )
                .accountingPointMeterState(
                        AccountingPointMeterState.builder()
                                .id(random.nextLong())

                                .build()
                )
                .calculationPeriod(new Date(random.nextLong()))
                .build()
        );




    }

    private String randomString(Random random) {
        final byte[] array = new byte[50];
        random.nextBytes(array);
        return new String(array, Charset.forName("UTF-8")); // or base32()
    }
}
