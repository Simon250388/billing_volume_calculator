package org.billing.accountingpoints.mapper;

import java.util.Collection;

public interface ModelMapper<M, D> {
  M toModel(D dto);

  Collection<M> toModel(Collection<D> dtos);

  D toDto(M model);

  Collection<D> toDto(Collection<M> models);
}
