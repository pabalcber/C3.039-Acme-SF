
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projectUserStories.ProjectUserStory;
import acme.entities.projects.Project;
import acme.entities.userStories.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select pus.userStory from ProjectUserStory pus where pus.project.manager.id = :id")
	Collection<UserStory> findManyUserStoriesByManagerId(int id);

	@Query("select u from UserStory u where u.id = :id")
	UserStory findOneUserStoryById(int id);

	@Query("select m from Manager m where m.id = :id")
	Manager findOneManagerById(int id);

	@Query("select pus from ProjectUserStory pus where pus.userStory.id = :id")
	ProjectUserStory findOneProjectUserStoryById(int id);

	@Query("select p from Project p where p.manager.id = :managerId")
	Collection<Project> findManyProjectsByManagerId(int managerId);

	@Query("select p from Project p")
	Collection<Project> findAllProjects();

}
