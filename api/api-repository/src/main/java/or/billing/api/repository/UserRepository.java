package or.billing.api.repository;

import or.billing.api.repository.model.UserDetailsModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDetailsModel, String> {}
