package com.best.billingvolumecalculator.models.enums;

import com.best.billingvolumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "square_types")
public class SquareType extends BaseCatalog {
    static int getCommonSquareTypeId() {
        return 1;
    }
}
