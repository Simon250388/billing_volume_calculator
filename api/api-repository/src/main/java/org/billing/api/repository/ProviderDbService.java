package org.billing.api.repository;

import org.springframework.stereotype.Service;

@Service
public interface ProviderDbService {
    boolean existsById(String id);
    boolean save(String id);

    void deleteAll();
}
