
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.userStories.Priority;
import acme.entities.userStories.UserStory;
import acme.roles.Manager;

@Service
public class ManagerUserStoryShowService extends AbstractService<Manager, UserStory> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerUserStoryRepository repository;


	// AbstractService interface ----------------------------------------------
	@Override
	public void authorise() {
		boolean status;
		int userStoryId;
		UserStory userStory;
		userStoryId = super.getRequest().getData("id", int.class);
		userStory = this.repository.findOneUserStoryById(userStoryId);
		status = super.getRequest().getPrincipal().hasRole(userStory.getProject().getManager()) || userStory != null && !userStory.isDraftMode();
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		UserStory object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneUserStoryById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		int managerId;
		Collection<Project> projects;
		SelectChoices projectChoices;
		SelectChoices priorityChoices;
		Dataset dataset;

		if (!object.isDraftMode())
			projects = this.repository.findAllProjects();
		else {
			managerId = super.getRequest().getPrincipal().getActiveRoleId();
			projects = this.repository.findManyProjectsByManagerId(managerId);
		}
		projectChoices = SelectChoices.from(projects, "title", object.getProject());
		priorityChoices = SelectChoices.from(Priority.class, object.getPriority());

		dataset = super.unbind(object, "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "optionalLink", "draftMode");

		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);

		dataset.put("priorities", priorityChoices);

		super.getResponse().addData(dataset);
	}
}
