package com.best.billing.volumecalculator.model;

public enum CalculationMethod {

    BY_METER(0, "По приборам учета"),
    BY_AVG_VOLUME(1, "По среднему"),
    BY_NORM(2, "По нормативу"),
    BY_AVG_NORM(3, "По среднему нормативу");

    private final int id;
    private final String description;

    CalculationMethod(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
