
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
public class ManagerProjectUserStoryDeleteService extends AbstractService<Manager, ProjectUserStory> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectUserStoryRepository repository;


	// AbstractService interface ----------------------------------------------
	@Override
	public void authorise() {
		boolean status;
		int projectId;
		Project project;

		projectId = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(projectId);
		status = project != null && !project.isDraftMode() && super.getRequest().getPrincipal().hasRole(project.getManager());

		super.getResponse().setAuthorised(status);
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

		projectId = super.getRequest().getData("masterId", int.class);
		project = this.repository.findOneProjectById(projectId);
		userStoryId = super.getRequest().getData("userStory", int.class);
		userStory = this.repository.findOneUserStoryById(userStoryId);
		object.setUserStory(userStory);
		object.setProject(project);

		super.bind(object, "projectUserStory");
	}

	@Override
	public void validate(final ProjectUserStory object) {
		assert object != null;

		int userStoryId;
		int projectId;
		boolean status;
		Collection<ProjectUserStory> projectUserStory;

		projectId = super.getRequest().getData("masterId", int.class);
		userStoryId = super.getRequest().getData("userStory", int.class);
		projectUserStory = this.repository.findProjectUserStoryByIds(projectId, userStoryId);
		status = projectUserStory.size() == 1;

		super.state(status, "*", "manager.project-user-story.form.empty");
	}

	@Override
	public void perform(final ProjectUserStory object) {
		assert object != null;

		int projectId;
		Project project;
		int userStoryId;
		UserStory userStory;

		projectId = super.getRequest().getData("masterId", int.class);
		userStoryId = super.getRequest().getData("userStory", int.class);
		project = this.repository.findOneProjectById(projectId);
		userStory = this.repository.findOneUserStoryById(userStoryId);
		object.setProject(project);
		object.setUserStory(userStory);

		this.repository.save(object);
	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		Collection<UserStory> projectUserStories;
		SelectChoices choices;

		projectUserStories = this.repository.findUserStoriesOfProjectId(object.getProject().getId());
		choices = SelectChoices.from(projectUserStories, "title", object.getUserStory());

		Dataset dataset;

		dataset = super.unbind(object, "project");
		dataset.put("masterId", object.getProject().getId());
		dataset.put("lecture", choices.getSelected().getKey());
		dataset.put("userStory", choices);
		super.getResponse().addData(dataset);
	}
}
