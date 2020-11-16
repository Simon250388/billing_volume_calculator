package com.best.billing.volumecalculator.helpers;

import com.best.billing.volumecalculator.model.ServiceVolumeValue;
import com.best.billing.volumecalculator.resolution.Resolution;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface Calculator {
    List<ServiceVolumeValue> calculate(@NonNull LocalDate calculationPeriod, @NonNull Resolution resolution);
}
