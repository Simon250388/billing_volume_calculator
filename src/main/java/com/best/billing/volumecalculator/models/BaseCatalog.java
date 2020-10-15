package com.best.billing.volumecalculator.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseCatalog extends BaseEntity {
    public BaseCatalog() {
        super();
    }

    @Column(name = "description", nullable = false, length = 50)
    private String description;
}
