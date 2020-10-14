package com.best.billing.volumecalculator.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class BaseCatalog extends BaseEntity {
    @Column(name = "description", nullable = false, length = 50)
    private String description;
}
