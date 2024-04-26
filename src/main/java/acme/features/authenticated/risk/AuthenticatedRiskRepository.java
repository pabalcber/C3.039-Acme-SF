
package acme.features.authenticated.risk;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.risks.Risk;

@Repository
public interface AuthenticatedRiskRepository extends AbstractRepository {

	@Query("select r from Risk r")
	Collection<Risk> findRisks();

	@Query("select i from Risk i where i.id = :id")
	Risk findOneRiskById(int id);

}
