package com.best.billing.volumecalculator.dto.catalog;

import com.best.billing.volumecalculator.dto.BaseCatalogDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointDTO extends BaseCatalogDTO {
    public AccountingPointDTO(Long id, String present) {
        super(id, present);
    }
}
