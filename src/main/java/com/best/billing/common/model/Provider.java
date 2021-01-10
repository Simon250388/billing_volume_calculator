package com.best.billing.common.model;


import com.best.billing.base.model.BaseCatalog;
import lombok.*;

import javax.persistence.*;

/**
 * Поставщик услуги
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "providers")
public class Provider implements BaseCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "description", nullable = false, length = 50)
    private String description;
}
