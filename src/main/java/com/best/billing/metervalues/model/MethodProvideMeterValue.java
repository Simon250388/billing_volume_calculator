package com.best.billing.metervalues.model;

public enum MethodProvideMeterValue {
    METER_DIFFERENTIATION_TYPE(1),
    ACCOUNTING_POINT_METER_STATE(2),
    ACCOUNTING_POINT_SERVICE_PROVIDER(3),
    ACCOUNTING_POINT_SERVICE_STATE(4);

    private final int id;

    MethodProvideMeterValue(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
