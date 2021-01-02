package com.best.billing.common.model.enums;

/**
 * Тип площади помещения
 */
public enum SquareType  {
    COMMON(SquareType.COMMON_SQUARE_ID,"Общая площадь помещения");
    public static final int COMMON_SQUARE_ID = 1;

    private final int id;

    private final String description;

    SquareType(int id, String description) {
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
