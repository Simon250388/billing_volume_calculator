package com.best.billing.volumecalculator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseCatalog extends BaseEntity {
    @Column(name = "description", nullable = false, length = 50)
    private String description;
}
