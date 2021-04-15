package com.best.billing.common.services;

import com.best.billing.base.service.BaseEntityService;
import com.best.billing.common.model.Building;
import lombok.NonNull;

public interface BuildingService extends BaseEntityService<Building> {
    Iterable<Building> findByDescriptionContaining(@NonNull final String subStr);
}
