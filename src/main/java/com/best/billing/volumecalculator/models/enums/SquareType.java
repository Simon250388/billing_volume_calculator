package com.best.billing.volumecalculator.models.enums;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "square_types")
public class SquareType extends BaseCatalog {
    static final long COMMON_SQUARE_TYPE_ID = 1;
}
