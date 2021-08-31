package org.billing.accountingpoints.usecase.convertor;

import java.util.List;
import java.util.Set;

public interface PresentDtoConvertor<P, D> {
  P toPresent(D dto);

  List<P> toPresent(Set<D> dto);
}
