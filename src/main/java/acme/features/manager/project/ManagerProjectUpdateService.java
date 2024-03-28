
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectUpdateService extends AbstractService<Manager, Project> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;


	// AbstractService interface ----------------------------------------------
	@Override
	public void authorise() {
		boolean status;
		int projectId;
		Manager manager;
		Project project;
		projectId = super.getRequest().getData("id", int.class);
		project = this.repository.findOneProjectById(projectId);
		manager = project == null ? null : project.getManager();
		status = super.getRequest().getPrincipal().hasRole(manager) && project != null && project.isDraftMode();
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

	/*
	 * @Override
	 * public void bind(final Project object) {
	 * assert object != null;
	 * 
	 * int contractorId;
	 * Company contractor;
	 * 
	 * contractorId = super.getRequest().getData("contractor", int.class);
	 * contractor = this.repository.findOneContractorById(contractorId);
	 * 
	 * super.bind(object, "reference", "title", "deadline", "salary", "score", "moreInfo", "description");
	 * object.setContractor(contractor);
	 * }
	 */

	@Override
	public void validate(final Project object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Project existing;

			existing = this.repository.findOneProjectByCode(object.getCode());
			super.state(existing == null, "code", "manager.project.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("cost"))
			super.state(object.getCost().getAmount() >= 0, "cost", "manager.project.form.error.negative-cost");
	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "title", "abstractPj", "indication", "cost", "optionalLink", "draftMode");
		dataset.put("managerId", object.getManager().getId());
		super.getResponse().addData(dataset);
	}

}
