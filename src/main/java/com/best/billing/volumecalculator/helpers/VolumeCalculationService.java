package com.best.billing.volumecalculator.helpers;

import com.best.billing.commonsettings.model.CalculationMethodByDirectionOfUse;
import com.best.billing.commonsettings.model.KeyNormValue;
import com.best.billing.commonsettings.model.RateValue;
import com.best.billing.commonsettings.model.SeasonalitySetting;
import com.best.billing.commonsettings.repository.*;
import com.best.billing.volumecalculator.model.AccountingPointServiceAvgVolume;
import com.best.billing.volumecalculator.model.ServiceVolumeValue;
import com.best.billing.volumecalculator.model.StabPeriod;
import com.best.billing.volumecalculator.repository.AccountingPointServiceAvgVolumeRepository;
import com.best.billing.volumecalculator.repository.StabPeriodRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VolumeCalculationService {

    private final PeriodSeasonalityRepository periodSeasonalityRepository;
    private final CalculationMethodByDirectionOfUseRepository calculationMethodByDirectionOfUseRepository;
    private final SeasonalitySettingsRepository seasonalitySettingsRepository;
    private final AccountingPointServiceAvgVolumeRepository accountingPointServiceAvgVolumeRepository;
    private final RateValueRepository rateValueRepository;
    private final KeyNormValueRepository keyNormValueRepository;
    private final StabPeriodRepository stabPeriodRepository;
    private final Calculator calculator;

    public VolumeCalculationService(
            PeriodSeasonalityRepository periodSeasonalityRepository,
            CalculationMethodByDirectionOfUseRepository calculationMethodByDirectionOfUseRepository,
            SeasonalitySettingsRepository seasonalitySettingsRepository,
            AccountingPointServiceAvgVolumeRepository accountingPointServiceAvgVolumeRepository,
            RateValueRepository rateValueRepository,
            KeyNormValueRepository keyNormValueRepository,
            StabPeriodRepository stabPeriodRepository, Calculator calculator) {
        this.periodSeasonalityRepository = periodSeasonalityRepository;
        this.calculationMethodByDirectionOfUseRepository = calculationMethodByDirectionOfUseRepository;
        this.seasonalitySettingsRepository = seasonalitySettingsRepository;
        this.accountingPointServiceAvgVolumeRepository = accountingPointServiceAvgVolumeRepository;
        this.rateValueRepository = rateValueRepository;
        this.keyNormValueRepository = keyNormValueRepository;
        this.stabPeriodRepository = stabPeriodRepository;
        this.calculator = calculator;
    }

    public List<ServiceVolumeValue> calculate(Date calculationPeriod) {
        Iterable<CalculationMethodByDirectionOfUse> calculationMethodByDirectionOfUse = calculationMethodByDirectionOfUseRepository.findAllLastByPeriod(calculationPeriod);
        Iterable<SeasonalitySetting> seasonalitySettingsSeasonalitySetting = seasonalitySettingsRepository.findAllLastByPeriod(calculationPeriod);
        Iterable<AccountingPointServiceAvgVolume> accountingPointServiceAvgVolumesIterable = accountingPointServiceAvgVolumeRepository.findAllLastByPeriod(calculationPeriod);
        Iterable<RateValue> rateValuesIterable = rateValueRepository.findAllLastByPeriod(calculationPeriod);
        Iterable<KeyNormValue> keyNormValuesIterable = keyNormValueRepository.findAllLastByPeriod(calculationPeriod);

        List<StabPeriod> stabPeriods = ImmutableList.copyOf(stabPeriodRepository.findAllByCalculationPeriod(calculationPeriod)).asList();
        ;
        List<CalculationMethodByDirectionOfUse> calculationMethodByDirectionOfUses = ImmutableList.copyOf(calculationMethodByDirectionOfUse).asList();
        List<SeasonalitySetting> seasonalitySettings = ImmutableList.copyOf(seasonalitySettingsSeasonalitySetting).asList();
        List<AccountingPointServiceAvgVolume> accountingPointServiceAvgVolumes = ImmutableList.copyOf(accountingPointServiceAvgVolumesIterable).asList();
        List<RateValue> rateValues = ImmutableList.copyOf(rateValuesIterable).asList();

        List<KeyNormValue> keyNormValues = ImmutableList.copyOf(keyNormValuesIterable).asList();

        return calculator.calculate(stabPeriods.stream()
                .map(stabPeriodItem ->
                        CalculationItem.builder()
                                .stabPeriod(stabPeriodItem)
                                .calculationMethodByDirectionOfUse(calculationMethodByDirectionOfUses.stream()
                                        .filter(item -> item.getService().equals(stabPeriodItem.getService())
                                                && item.getDirectionOfUse().equals(stabPeriodItem.getDirectionOfUse()))
                                        .findAny())
                                .seasonalitySettingsByBuilding(seasonalitySettings.stream().filter(
                                        item -> item.getService().equals(stabPeriodItem.getService()) &&
                                                item.getDirectionOfUse().equals(stabPeriodItem.getDirectionOfUse()) &&
                                                item.getBuilding().equals(stabPeriodItem.getAccountingPointKeyRoomServiceEntity().getAccountingPointKeyRoom().getKeyRoom().getBuilding()))
                                        .findAny())
                                .seasonalitySetting(seasonalitySettings.stream().filter(
                                        item -> item.getService().equals(stabPeriodItem.getService()) &&
                                                item.getDirectionOfUse().equals(stabPeriodItem.getDirectionOfUse()) &&
                                                item.getBuilding() == null)
                                        .findAny())
                                .accountingPointServiceAvgVolume(accountingPointServiceAvgVolumes.stream().filter(
                                        item -> item.getAccountingPointKeyRoomServiceEntity().equals(stabPeriodItem.getAccountingPointKeyRoomServiceEntity())
                                                && item.getServicePart().equals(stabPeriodItem.getService()))
                                        .findAny())
                                .rateValue(rateValues.stream().filter(
                                        item -> item.getService().equals(stabPeriodItem.getService())
                                                && item.getRateGroup().equals(stabPeriodItem.getRoomRateGroup()))
                                        .findAny())
                                .keyNormValue(keyNormValues.stream().filter(
                                        item -> item.getKeyNorm().equals(stabPeriodItem.getRoomServiceKeyNorm().getKeyNorm()))
                                        .findAny())
                                .build()
                ));
    }
}
