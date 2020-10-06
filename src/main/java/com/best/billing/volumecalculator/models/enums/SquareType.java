package com.best.billing.volumecalculator.models.enums;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "square_types")
public class SquareType extends BaseCatalog {
    static final long COMMON_SQUARE_TYPE_ID = 1;
}
