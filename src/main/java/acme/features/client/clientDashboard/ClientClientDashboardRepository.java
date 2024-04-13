
package acme.features.client.clientDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ClientClientDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.contract.draftMode = false AND pl.completeness < 25.0")
	int totalProgressLogsBelow25Percent();

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.contract.draftMode = false AND pl.completeness >= 25.0 AND pl.completeness < 50.0")
	int totalProgressLogs25to50Percent();

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.contract.draftMode = false AND pl.completeness >= 50.0 AND pl.completeness < 75.0")
	int totalProgressLogs50to75Percent();

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.contract.draftMode = false AND pl.completeness >= 75.0")
	int totalProgressLogsAbove75Percent();

}
