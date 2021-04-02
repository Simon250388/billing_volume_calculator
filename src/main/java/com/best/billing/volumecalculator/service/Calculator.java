package com.best.billing.volumecalculator.service;

import com.best.billing.departmen.customer.CalculationResult;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomEventsJournal;
import com.best.billing.resolution.Resolution;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Calculator {
    List<Map<RoomEvent, List<CalculationResult>>> calculate(@NonNull LocalDate calculationPeriod, @NonNull Resolution resolution, RoomEventsJournal eventsJournal);
}
