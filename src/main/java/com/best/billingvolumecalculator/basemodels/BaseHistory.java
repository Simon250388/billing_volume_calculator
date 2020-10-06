package com.best.billingvolumecalculator.basemodels;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseHistory extends BaseEntity {

    public BaseHistory() {
        super();
    }

    private Date period;

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }
}
