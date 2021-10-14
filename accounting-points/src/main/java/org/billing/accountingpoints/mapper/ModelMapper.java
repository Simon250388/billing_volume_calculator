package org.billing.accountingpoints.mapper;

import java.util.List;

public interface ModelMapper<M, D> {
  M toModel(D dto);

  List<M> toModel(List<D> dtos);

  D toDto(M model);

  List<D> toDto(List<M> models);
}
