package com.best.billing.common.model.enums;

/**
 * Значение состояния прибора учета
 */
public enum MeterState {
    ACTIVE(1, "Установлен"),
    DISABLE(2, "Снят"),
    VERIFICATION(3, "На поверке");
    private final int id;
    private final String description;

    MeterState(int id, String description) {
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
