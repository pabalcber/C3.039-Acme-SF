
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.userStories.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.manager.id = :id")
	Collection<Project> findManyProjectsByManagerId(int id);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select m from Manager m where m.id = :id")
	Manager findOneManagerById(int id);

	@Query("select p from Project p where p.code = :code")
	Project findOneProjectByCode(String code);

	@Query("select pus from ProjectUserStory pus where pus.project.id = :id")
	Collection<UserStory> findManyUserStoriesByProjectId(int id);

	@Query("select count(pus.userStory) from ProjectUserStory pus where pus.project.id = :id")
	int computeUserStoriesByProjectId(int id);
}
