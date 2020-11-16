package com.best.billing.base.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

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
    private LocalDateTime period;
}
