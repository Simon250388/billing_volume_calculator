package com.best.billing.volumecalculator.dto;

public abstract class BaseEntityDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
