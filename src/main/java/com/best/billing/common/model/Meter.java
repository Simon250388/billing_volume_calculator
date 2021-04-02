package com.best.billing.common.model;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;

import javax.persistence.*;

/**
 * Прибор учета
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "meters")
public class Meter implements BaseCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "description", nullable = false, length = 50)
    private String description;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "meter_type_id", nullable = false)
    private MeterType meterType;
}
