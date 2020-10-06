package com.best.billing.volumecalculator.basemodels;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseHistory extends BaseEntity {
    private Date period;

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }
}
