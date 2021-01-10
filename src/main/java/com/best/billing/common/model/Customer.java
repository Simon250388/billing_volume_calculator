package com.best.billing.common.model;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;

import javax.persistence.*;

/**
 * Контрагент
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "customers")
public class Customer implements BaseCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "description", nullable = false, length = 50)
    private String description;
}
