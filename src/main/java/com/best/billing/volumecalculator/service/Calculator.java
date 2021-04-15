package com.best.billing.volumecalculator.service;

import com.best.billing.departmen.customer.CalculationResult;
import com.best.billing.departmen.customer.CalculationSettings;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomEventsJournal;
import com.best.billing.resolution.Resolution;

import java.util.List;
import java.util.Map;

public interface Calculator {
    List<Map<RoomEvent, List<CalculationResult>>> calculate(
            CalculationSettings calculationSettings, Resolution resolution, RoomEventsJournal eventsJournal);
}
