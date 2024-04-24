
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projectUserStories.ProjectUserStory;
import acme.entities.projects.Project;
import acme.entities.userStories.Priority;
import acme.entities.userStories.UserStory;
import acme.roles.Manager;

@Service
public class ManagerUserStoryUpdateService extends AbstractService<Manager, UserStory> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerUserStoryRepository repository;


	// AbstractService interface ----------------------------------------------
	@Override
	public void authorise() {
		boolean status;
		int userStoryId;
		UserStory userStory;
		ProjectUserStory project;

		userStoryId = super.getRequest().getData("id", int.class);
		userStory = this.repository.findOneUserStoryById(userStoryId);
		project = this.repository.findOneProjectUserStoryById(userStoryId);

		status = super.getRequest().getPrincipal().hasRole(project.getProject().getManager()) || userStory != null && !userStory.isDraftMode();
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
	public void bind(final UserStory object) {
		assert object != null;

		int projectId;
		ProjectUserStory project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectUserStoryById(projectId);

		super.bind(object, "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "optionalLink");
		project.setUserStory(object);
	}

	@Override
	public void validate(final UserStory object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("estimatedCost"))
			super.state(object.getEstimatedCost() >= 0, "cost", "manager.userStory.form.error.negative-estimated-cost");
	}

	@Override
	public void perform(final UserStory object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		int managerId;
		Collection<Project> projects;
		SelectChoices projectChoices;
		SelectChoices priorityChoices;
		Dataset dataset;
		ProjectUserStory project;

		if (!object.isDraftMode())
			projects = this.repository.findAllProjects();
		else {
			managerId = super.getRequest().getPrincipal().getActiveRoleId();
			projects = this.repository.findManyProjectsByManagerId(managerId);
		}

		project = this.repository.findOneProjectUserStoryById(object.getId());
		projectChoices = SelectChoices.from(projects, "title", project.getProject());
		priorityChoices = SelectChoices.from(Priority.class, object.getPriority());

		dataset = super.unbind(object, "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "optionalLink", "draftMode");

		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);

		dataset.put("priorities", priorityChoices);

		super.getResponse().addData(dataset);
	}

}
