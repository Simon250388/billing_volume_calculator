package org.billing.accountingpoints.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/** Точка учета. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounting_points")
public class AccountingPoint {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;

  @Column(name = "description", nullable = false, length = 50)
  private String description;

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(description).toHashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AccountingPoint that = (AccountingPoint) o;
    return new EqualsBuilder().append(id, that.id).isEquals();
  }
}
