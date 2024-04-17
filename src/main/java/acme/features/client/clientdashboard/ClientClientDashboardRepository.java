
package acme.features.client.clientdashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ClientClientDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.contract.draftMode = false AND pl.completeness < 25.0 AND pl.contract.client.id = :clientId")
	int totalProgressLogsBelow25Percent(Integer clientId);

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.contract.draftMode = false AND pl.completeness >= 25.0 AND pl.completeness < 50.0 AND pl.contract.client.id = :clientId")
	int totalProgressLogs25to50Percent(Integer clientId);

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.contract.draftMode = false AND pl.completeness >= 50.0 AND pl.completeness < 75.0 AND pl.contract.client.id = :clientId")
	int totalProgressLogs50to75Percent(Integer clientId);

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.contract.draftMode = false AND pl.completeness >= 75.0 AND pl.contract.client.id = :clientId")
	int totalProgressLogsAbove75Percent(Integer clientId);

	@Query("SELECT AVG(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'EUR'")
	Double averageBudgetsInEuros(Integer clientId);

	@Query("SELECT AVG(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'GBP'")
	Double averageBudgetsInUSD(Integer clientId);

	@Query("SELECT AVG(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'USD'")
	Double averageBudgetsInGBP(Integer clientId);

	@Query("SELECT STDDEV(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'EUR'")
	Double deviationOfBudgetsInEUR(Integer clientId);

	@Query("SELECT STDDEV(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'GBP'")
	Double deviationOfBudgetsInGBP(Integer clientId);

	@Query("SELECT STDDEV(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'USD'")
	Double deviationOfBudgetsInUSD(Integer clientId);

	@Query("SELECT MIN(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'EUR'")
	Double minimumBudgetInEUR(Integer clientId);

	@Query("SELECT MIN(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'USD'")
	Double minimumBudgetInUSD(Integer clientId);

	@Query("SELECT MIN(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'GBP'")
	Double minimumBudgetInGBP(Integer clientId);

	@Query("SELECT MAX(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'EUR'")
	Double maximumBudgetInEUR(Integer clientId);

	@Query("SELECT MAX(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'USD'")
	Double maximumBudgetInUSD(Integer clientId);

	@Query("SELECT MAX(c.budget.amount) FROM Contract c WHERE c.draftMode = false AND c.client.id = :clientId AND c.budget.currency = 'GBP'")
	Double maximumBudgetInGBP(Integer clientId);

}
