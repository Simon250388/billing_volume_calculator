package com.best.billing.common.model;

import com.best.billing.base.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 *
 * Ключ норматива
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "key_norms")
public class KeyNorm implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
}
