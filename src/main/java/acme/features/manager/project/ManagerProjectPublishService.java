
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectPublishService extends AbstractService<Manager, Project> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int projectId;
		Project project;
		Manager manager;

		projectId = super.getRequest().getData("id", int.class);
		project = this.repository.findOneProjectById(projectId);
		manager = project == null ? null : project.getManager();
		status = project != null && project.isDraftMode() && super.getRequest().getPrincipal().hasRole(manager);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Project object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProjectById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Project object) {
		assert object != null;

		super.bind(object, "code", "title", "abstractPj", "fatalErrors", "cost", "optionalLink");
	}

	@Override
	public void validate(final Project object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Project existing;

			existing = this.repository.findOneProjectByCode(object.getCode());
			super.state(existing == null || existing.equals(object), "code", "manager.project.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("cost"))
			super.state(object.getCost().getAmount() >= 0, "cost", "manager.project.form.error.negative-cost");

		if (!super.getBuffer().getErrors().hasErrors("fatalErrors"))
			super.state(!object.isFatalErrors(), "fatalErrors", "manager.project.form.error.cant-publish-fatal-errors-project");

		{
			int amountUserStories;

			amountUserStories = this.repository.computeUserStoriesByProjectId(object.getId());
			super.state(amountUserStories <= 0, "*", "manager.project.form.error.no-user-story-in-project");
		}
	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "title", "abstractPj", "fatalErrors", "cost", "optionalLink", "draftMode");
		dataset.put("managerId", object.getManager().getId());
		super.getResponse().addData(dataset);
	}

}
