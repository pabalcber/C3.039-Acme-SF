
package acme.features.client.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ClientDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.completeness < 25")
	int totalProgressLogsBelow25Percent();

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.completeness >= 25 AND pl.completeness < 50")
	int totalProgressLogs25To50Percent();

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.completeness >= 50 AND pl.completeness < 75")
	int totalProgressLogs50To75Percent();

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.completeness >= 75")
	int totalProgressLogsAbove75Percent();

}
