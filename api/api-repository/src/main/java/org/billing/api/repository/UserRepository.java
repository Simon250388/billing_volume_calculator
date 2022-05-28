package org.billing.api.repository;

import org.billing.api.repository.model.UserDetailsModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDetailsModel, String> {}
