package com.best.billing.servicebuilder.models.enums;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "square_types")
@Immutable
public class SquareType extends BaseCatalog {
    public static final long COMMON_SQUARE_TYPE_ID = 1;
}
