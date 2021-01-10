package com.best.billing.base.model;

import java.time.LocalDateTime;

public interface BaseHistory extends BaseEntity {
    LocalDateTime getPeriod();
    void setPeriod(LocalDateTime period);
}
