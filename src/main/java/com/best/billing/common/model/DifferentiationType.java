package com.best.billing.common.model;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;

import javax.persistence.*;

/**
 * Вид диффиринцированности
 */
@Data
@Builder
@Entity
@Table(name = "differentiation_types")
public class DifferentiationType implements BaseCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "description", nullable = false, length = 50)
    private String description;
}
