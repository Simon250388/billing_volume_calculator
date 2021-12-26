package org.billing.api.auth;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDetailsModel, String> {}
