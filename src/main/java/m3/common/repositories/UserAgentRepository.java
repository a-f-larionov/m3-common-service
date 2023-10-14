package m3.common.repositories;

import m3.common.entities.UserAgentEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserAgentRepository extends CrudRepository<UserAgentEntity, Long> {

}
