package com.best.billing.common.model;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Строение
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(name = "buildings")
public class Building implements BaseCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "description", nullable = false, length = 50)
    private String description;
}
