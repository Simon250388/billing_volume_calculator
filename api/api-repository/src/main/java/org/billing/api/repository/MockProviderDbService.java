package org.billing.api.repository;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class MockProviderDbService implements ProviderDbService {
  private final Set<String> dataSet = new HashSet<>();

  @Override
  public boolean existsById(String id) {
    return dataSet.contains(id);
  }

  @Override
  public boolean save(String id) {
    return dataSet.add(id);
  }
}
