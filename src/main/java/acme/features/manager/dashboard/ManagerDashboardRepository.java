
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.userStories.Priority;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select count(pus.userStory) from ProjectUserStory pus where pus.userStory.priority = :priority and pus.project.manager.id = :managerId and pus.userStory.draftMode=false")
	int priorityUserStories(int managerId, Priority priority);

	@Query("select avg(pus.userStory.estimatedCost) from ProjectUserStory pus where pus.project.manager.id = :managerId and pus.userStory.draftMode=false")
	Double averageEstimatedCost(int managerId);

	@Query("select stddev(pus.userStory.estimatedCost) from ProjectUserStory pus where pus.project.manager.id = :managerId and pus.userStory.draftMode=false")
	Double deviationEstimatedCost(int managerId);

	@Query("select min(pus.userStory.estimatedCost) from ProjectUserStory pus where pus.project.manager.id = :managerId and pus.userStory.draftMode=false")
	Double minimumEstimatedCost(int managerId);

	@Query("select max(pus.userStory.estimatedCost) from ProjectUserStory pus where pus.project.manager.id = :managerId and pus.userStory.draftMode=false")
	Double maximumEstimatedCost(int managerId);

	@Query("select avg(p.cost.amount) from Project p where p.cost.currency = :currency and p.manager.id = :managerId and p.draftMode=false")
	Double averageProjectCosts(String currency, int managerId);

	@Query("select stddev(p.cost.amount) from Project p where p.cost.currency = :currency and p.manager.id = :managerId and p.draftMode=false")
	Double deviationProjectCosts(String currency, int managerId);

	@Query("select min(p.cost.amount) from Project p where p.cost.currency = :currency and p.manager.id = :managerId and p.draftMode=false")
	Double minimumProjectCosts(String currency, int managerId);

	@Query("select max(p.cost.amount) from Project p where p.cost.currency = :currency and p.manager.id = :managerId and p.draftMode=false")
	Double maximumProjectCosts(String currency, int managerId);

}
