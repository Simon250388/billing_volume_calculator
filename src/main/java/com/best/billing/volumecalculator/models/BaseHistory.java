package com.best.billing.volumecalculator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class BaseHistory extends BaseEntity {

    public BaseHistory() {
        super();
    }

    @Column(name = "period", nullable = false)
    private Date period;
}
