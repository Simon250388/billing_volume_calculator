package com.best.billing.volumecalculator.helpers;

import com.best.billing.volumecalculator.model.ServiceVolumeValue;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public interface Calculator {
    List<ServiceVolumeValue> calculate(@NonNull Date calculationPeriod);
}
