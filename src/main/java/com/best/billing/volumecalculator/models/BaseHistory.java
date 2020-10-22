package com.best.billing.volumecalculator.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseHistory extends BaseEntity {
    public BaseHistory() {
        super();
    }

    @Column(name = "period", nullable = false)
    private Date period;
}
