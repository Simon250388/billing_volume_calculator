package org.billing.api.repository;

import org.springframework.stereotype.Service;

@Service
public interface AccountServiceDbService {
    boolean existsById(String id);
    boolean save(String id);
}
