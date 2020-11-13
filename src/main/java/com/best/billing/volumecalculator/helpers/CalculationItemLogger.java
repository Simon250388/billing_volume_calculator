package com.best.billing.volumecalculator.helpers;

import lombok.NonNull;

public interface CalculationItemLogger {
    void addStepVolume(@NonNull String calculationType, @NonNull String result, @NonNull String details);
    void addStepVolumeFact(@NonNull String calculationType, @NonNull String result, @NonNull String details);
}
