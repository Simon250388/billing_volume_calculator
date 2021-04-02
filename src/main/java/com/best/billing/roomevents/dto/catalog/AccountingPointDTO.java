package com.best.billing.roomevents.dto.catalog;

import com.best.billing.base.dto.BaseCatalogDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointDTO extends BaseCatalogDTO {
    public AccountingPointDTO(Long id, Long version, String present) {
        super(id, version, present);
    }
}
