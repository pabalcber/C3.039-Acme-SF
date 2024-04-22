
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.userStories.Priority;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select count(u) from UserStory u where u.priority = :priority and u.project.manager.id = :managerId")
	int priorityUserStories(int managerId, Priority priority);
	@Query("select avg(u.estimatedCost) from UserStory u where u.project.manager.id = :managerId")
	Double averageEstimatedCost(int managerId);

	@Query("select stddev(u.estimatedCost) from UserStory u where u.project.manager.id = :managerId")
	Double deviationEstimatedCost(int managerId);

	@Query("select min(u.estimatedCost) from UserStory u where u.project.manager.id = :managerId")
	Double minimumEstimatedCost(int managerId);

	@Query("select max(u.estimatedCost) from UserStory u where u.project.manager.id = :managerId")
	Double maximumEstimatedCost(int managerId);

	@Query("select avg(p.cost.amount) from Project p where p.cost.currency = 'EUR' and p.manager.id = :managerId")
	Double averageProjectCostsEUR(int managerId);

	@Query("select avg(p.cost.amount) from Project p where p.cost.currency = 'GBP' and p.manager.id = :managerId")
	Double averageProjectCostsGBP(int managerId);

	@Query("select avg(p.cost.amount) from Project p where p.cost.currency = 'USD' and p.manager.id = :managerId")
	Double averageProjectCostsUSD(int managerId);

	@Query("select stddev(p.cost.amount) from Project p where p.cost.currency = 'EUR' and p.manager.id = :managerId")
	Double deviationProjectCostsEUR(int managerId);

	@Query("select stddev(p.cost.amount) from Project p where p.cost.currency = 'GBP' and p.manager.id = :managerId")
	Double deviationProjectCostsGBP(int managerId);

	@Query("select stddev(p.cost.amount) from Project p where p.cost.currency = 'USD' and p.manager.id = :managerId")
	Double deviationProjectCostsUSD(int managerId);

	@Query("select min(p.cost.amount) from Project p where p.cost.currency = 'EUR' and p.manager.id = :managerId")
	Double minimumProjectCostsEUR(int managerId);

	@Query("select min(p.cost.amount) from Project p where p.cost.currency = 'GBP' and p.manager.id = :managerId")
	Double minimumProjectCostsGBP(int managerId);

	@Query("select min(p.cost.amount) from Project p where p.cost.currency = 'USD' and p.manager.id = :managerId")
	Double minimumProjectCostsUSD(int managerId);

	@Query("select max(p.cost.amount) from Project p where p.cost.currency = 'EUR' and p.manager.id = :managerId")
	Double maximumProjectCostsEUR(int managerId);

	@Query("select max(p.cost.amount) from Project p where p.cost.currency = 'GBP' and p.manager.id = :managerId")
	Double maximumProjectCostsGBP(int managerId);

	@Query("select max(p.cost.amount) from Project p where p.cost.currency = 'USD' and p.manager.id = :managerId")
	Double maximumProjectCostsUSD(int managerId);

}
