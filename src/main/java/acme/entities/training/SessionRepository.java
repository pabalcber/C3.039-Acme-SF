
package acme.entities.training;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {

	long countByFurtherInformationLinkIsNotNull();
}
