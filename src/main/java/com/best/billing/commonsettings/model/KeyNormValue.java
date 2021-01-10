package com.best.billing.commonsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.KeyNorm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Значение норматива потребления
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "rate_values")
public class KeyNormValue implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "key_norm_Id", nullable = false)
    private KeyNorm keyNorm;
    @Column(nullable = false)
    private int normValue;
}
