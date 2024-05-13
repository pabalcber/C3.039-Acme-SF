
package acme.features.client.progresslog;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.clients.Client;

@Repository
public interface ClientProgressLogRepository extends AbstractRepository {

	@Query("select c from Contract c where c.id = :masterId")
	Contract findOneContractById(int masterId);

	@Query("select pl from ProgressLog pl where pl.contract.id = :masterId")
	Collection<ProgressLog> findManyProgressLogsByMasterId(int masterId);

	@Query("select pl.contract from ProgressLog pl where pl.id = :id")
	Contract findOneContractByProgressLogId(int id);

	@Query("select pl from ProgressLog pl where pl.id = :id")
	ProgressLog findOneProgressLogById(int id);

	@Query("select pl from ProgressLog pl where pl.recordId = :recordId")
	ProgressLog findOneProgressLogByRecordId(String recordId);

	@Query("SELECT cl FROM Client cl WHERE cl.userAccount.id = :accountId")
	Client findClientByAccountId(int accountId);

	@Query("SELECT c.client FROM Contract c WHERE c.id = :masterId")
	Client findClientByMasterId(int masterId);

	@Query("SELECT p FROM ProgressLog p WHERE p.contract.id = :masterId")
	ProgressLog findProgressLogByMasterId(int masterId);

}
