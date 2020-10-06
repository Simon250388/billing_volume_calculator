package com.best.billingvolumecalculator.dto;

public abstract class BaseEntityDTO {
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
