
package acme.entities.training;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {

	@Query("SELECT COUNT(s) FROM Session s WHERE s.furtherInformationLink IS NOT NULL")
	Long countSessionsWithLink();

}
