package com.best.billingvolumecalculator.dto;

public abstract class BaseHistoryDTO extends BaseEntityDTO {
    private String period;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
