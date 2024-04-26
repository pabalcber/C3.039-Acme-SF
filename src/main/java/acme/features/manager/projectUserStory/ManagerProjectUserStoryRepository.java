
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projectUserStories.ProjectUserStory;
import acme.entities.projects.Project;
import acme.entities.userStories.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerProjectUserStoryRepository extends AbstractRepository {

	@Query("select p from Project p where p.id=:projectId")
	Project findOneProjectById(int projectId);

	@Query("select m from Manager m where m.id=:managerId")
	Manager findOneManagerById(int managerId);

	@Query("select u from UserStory u where u.id=:userStoryId")
	UserStory findOneUserStoryById(int userStoryId);

	@Query("select u from UserStory u where u.manager.id=:managerId")
	Collection<UserStory> findAllUserStoriesOfManagerId(int managerId);

	@Query("select pus.userStory from ProjectUserStory pus where pus.project.id=:id")
	Collection<UserStory> findUserStoriesOfProjectId(int id);

	@Query("select pus from ProjectUserStory pus where pus.project.id=:projectId and pus.userStory.id=:userStoryId")
	Collection<ProjectUserStory> findProjectUserStoryByIds(int projectId, int userStoryId);

}
