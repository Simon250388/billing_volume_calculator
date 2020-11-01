package com.best.billing.volumecalculator.service;

import com.best.billing.common.model.Service;
import com.best.billing.commonsettings.model.*;
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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.CompletableFuture;

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

        CompletableFuture<Iterable<PeriodSeasonality>> periodSeasonalityCompletableFuture = periodSeasonalityRepository.findAllLastByPeriodAsync(calculationPeriod);
        CompletableFuture<Iterable<CalculationMethodByDirectionOfUse>> calculationMethodByDirectionOfUseCompletableFuture = calculationMethodByDirectionOfUseRepository.findAllLastByPeriodAsync(calculationPeriod);
        CompletableFuture<Iterable<SeasonalitySetting>> seasonalitySettingsSeasonalitySettingCompletableFuture = seasonalitySettingsRepository.findAllLastByPeriodAsync(calculationPeriod);
        CompletableFuture<Iterable<AccountingPointServiceAvgVolume>> accountingPointServiceAvgVolumesIterableCompletableFuture = accountingPointServiceAvgVolumeRepository.findAllLastByPeriodAsync(calculationPeriod);
        CompletableFuture<Iterable<RateValue>> rateValuesIterableCompletableFuture = rateValueRepository.findAllLastByPeriodAsync(calculationPeriod);
        CompletableFuture<Iterable<KeyNormValue>> keyNormValuesIterableCompletableFuture = keyNormValueRepository.findAllLastByPeriodAsync(calculationPeriod);

        CompletableFuture.allOf(
                periodSeasonalityCompletableFuture,
                calculationMethodByDirectionOfUseCompletableFuture,
                seasonalitySettingsSeasonalitySettingCompletableFuture,
                seasonalitySettingsSeasonalitySettingCompletableFuture,
                accountingPointServiceAvgVolumesIterableCompletableFuture,
                rateValuesIterableCompletableFuture,
                keyNormValuesIterableCompletableFuture).join();

        List<CalculationMethodByDirectionOfUse> calculationMethodByDirectionOfUses = ImmutableList.copyOf(calculationMethodByDirectionOfUseCompletableFuture.join()).asList();
        List<SeasonalitySetting> seasonalitySettings = ImmutableList.copyOf(seasonalitySettingsSeasonalitySettingCompletableFuture.join()).asList();
        List<AccountingPointServiceAvgVolume> accountingPointServiceAvgVolumes = ImmutableList.copyOf(accountingPointServiceAvgVolumesIterableCompletableFuture.join()).asList();
        List<RateValue> rateValues = ImmutableList.copyOf(rateValuesIterableCompletableFuture.join()).asList();
        List<KeyNormValue> keyNormValues = ImmutableList.copyOf(keyNormValuesIterableCompletableFuture.join()).asList();

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

        stabPeriodsStream.stream()
                .map(stabPeriodItem -> new Object() {

                    StabPeriod stabPeriod = stabPeriodItem;

                    final Optional<CalculationMethodByDirectionOfUse> calculationMethodByDirectionOfUse = calculationMethodByDirectionOfUses.stream()
                            .filter(item -> item.getService().equals(stabPeriodItem.getService())
                                    && item.getDirectionOfUse().equals(stabPeriodItem.getDirectionOfUse()))
                            .findAny();

                    final Optional<SeasonalitySetting> seasonalitySettingsByBuilding = seasonalitySettings.stream().filter(
                            item -> item.getService().equals(stabPeriodItem.getService()) &&
                                    item.getDirectionOfUse().equals(stabPeriodItem.getDirectionOfUse()) &&
                                    item.getBuilding().equals(stabPeriodItem.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getKeyRoom().getBuilding()))
                            .findAny();

                    final Optional<SeasonalitySetting> seasonalitySetting = seasonalitySettings.stream().filter(
                            item -> item.getService().equals(stabPeriodItem.getService()) &&
                                    item.getDirectionOfUse().equals(stabPeriodItem.getDirectionOfUse()) &&
                                    item.getBuilding() == null)
                            .findAny();

                    final Optional<AccountingPointServiceAvgVolume> accountingPointServiceAvgVolume = accountingPointServiceAvgVolumes.stream().filter(
                            item -> item.getAccountingPointKeyRoomServiceEntity().equals(stabPeriodItem.getAccountingPointKeyRoomServiceEntity())
                                    && item.getServicePart().equals(stabPeriodItem.getService()))
                            .findAny();

                    final Optional<RateValue> rateValue = rateValues.stream().filter(
                            item -> item.getService().equals(stabPeriodItem.getService())
                                    && item.getRateGroup().equals(stabPeriodItem.getRoomRateGroup()))
                            .findAny();

                    final Optional<KeyNormValue> keyNormValue = keyNormValues.stream().filter(
                            item -> item.getKeyNorm().equals(stabPeriodItem.getRoomServiceKeyNorm().getKeyNorm()))
                            .findAny();

                });
    }

    private String randomString(Random random) {
        final byte[] array = new byte[50];
        random.nextBytes(array);
        return new String(array, Charset.forName("UTF-8")); // or base32()
    }
}
