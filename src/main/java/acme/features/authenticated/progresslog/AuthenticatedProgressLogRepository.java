
package acme.features.authenticated.progresslog;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.ProgressLog;

@Repository
public interface AuthenticatedProgressLogRepository extends AbstractRepository {

	@Query("select pl from ProgressLog pl where pl.contract.id = :masterId")
	Collection<ProgressLog> findManyProgressLogsByMasterId(int masterId);

	@Query("select pl from ProgressLog pl where pl.id = :id")
	ProgressLog findOneProgressLogById(int id);

}
