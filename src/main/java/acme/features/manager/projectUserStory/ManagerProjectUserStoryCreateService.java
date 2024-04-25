
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projectUserStories.ProjectUserStory;
import acme.entities.projects.Project;
import acme.entities.userStories.UserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectUserStoryCreateService extends AbstractService<Manager, ProjectUserStory> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectUserStoryRepository repository;


	// AbstractService interface ----------------------------------------------
	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		ProjectUserStory object;
		int projectId;
		Project project;

		projectId = super.getRequest().getData("masterId", int.class);
		project = this.repository.findOneProjectById(projectId);

		object = new ProjectUserStory();
		object.setUserStory(null);
		object.setProject(project);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProjectUserStory object) {
		assert object != null;

		int projectId;
		Project project;
		int userStoryId;
		UserStory userStory;

		super.bind(object, "projectUserStory");
		projectId = super.getRequest().getData("masterId", int.class);
		project = this.repository.findOneProjectById(projectId);
		object.setProject(project);
		userStoryId = super.getRequest().getData("userStory", int.class);
		userStory = this.repository.findOneUserStoryById(userStoryId);
		object.setUserStory(userStory);
	}

	@Override
	public void validate(final ProjectUserStory object) {
		assert object != null;

	}

	@Override
	public void perform(final ProjectUserStory object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		int managerId;
		Collection<UserStory> allUserStories;
		Collection<UserStory> projectUserStories;
		SelectChoices choices;

		managerId = super.getRequest().getPrincipal().getActiveRoleId();
		allUserStories = this.repository.findAllUserStoriesOfManagerId(managerId);
		projectUserStories = this.repository.findUserStoriesOfProjectId(object.getProject().getId());
		allUserStories.removeAll(projectUserStories);
		choices = SelectChoices.from(allUserStories, "title", object.getUserStory());

		Dataset dataset;

		dataset = super.unbind(object, "project");
		dataset.put("masterId", object.getProject().getId());
		dataset.put("userStory", choices.getSelected().getKey());
		dataset.put("userStories", choices);
		super.getResponse().addData(dataset);
	}
}
