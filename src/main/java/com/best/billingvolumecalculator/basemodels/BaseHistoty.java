package com.best.billingvolumecalculator.basemodels;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseHistoty extends BaseEntity {
    private Date period;

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }
}
