package com.best.billing.volumecalculator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class BaseHistory extends BaseEntity {
    @Column(name = "period", nullable = false)
    private Date period;
}
