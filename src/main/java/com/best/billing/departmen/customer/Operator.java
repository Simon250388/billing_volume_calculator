package com.best.billing.departmen.customer;

import java.time.LocalDate;

public interface Operator {
    void getCalculations(LocalDate calculationPeriod, Long keyRoomId);
    void getCalculations(LocalDate calculationPeriod);
}
