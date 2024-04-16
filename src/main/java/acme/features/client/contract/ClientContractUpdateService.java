
package acme.features.client.contract;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contracts.Contract;
import acme.entities.projects.Project;
import acme.roles.clients.Client;

@Service
public class ClientContractUpdateService extends AbstractService<Client, Contract> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientContractRepository repository;

	// AbstractService<Client, Contract> -------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Contract contract;
		Client client;

		masterId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(masterId);
		client = contract == null ? null : contract.getClient();
		status = contract != null && contract.isDraftMode() && super.getRequest().getPrincipal().hasRole(client);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Contract object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findContractById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Contract object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);

		super.bind(object, "code", "instantiationMoment", "providerName", "customerName", "goals", "budget");
		object.setProject(project);
	}

	@Override
	public void validate(final Contract object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Contract existing;

			existing = this.repository.findOneContractByCode(object.getCode());
			super.state(existing == null || existing.equals(object), "code", "client.contract.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("budget")) {
			Money budget = object.getBudget();
			Project project = object.getProject();

			super.state(budget.getAmount() >= 0, "budget", "client.contract.form.error.negative-budget");

			if (project != null) {
				Money projectCost = project.getCost();

				if (!budget.getCurrency().equals(projectCost.getCurrency()))
					super.state(false, "budget", "client.contract.form.error.different-currency");

				if (budget.getAmount() > projectCost.getAmount())
					super.state(false, "budget", "client.contract.form.error.budget-exceeds-project-cost");
			}
		}
	}

	@Override
	public void perform(final Contract object) {
		assert object != null;

		Client client;

		client = this.repository.findClientById(super.getRequest().getPrincipal().getActiveRoleId());

		object.setCustomerName(client.getIdentification());
		this.repository.save(object);
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;

		int clientId;
		Collection<Project> projects;
		SelectChoices choices;
		Dataset dataset;
		int id;
		Project project;
		Client client;

		clientId = super.getRequest().getPrincipal().getActiveRoleId();
		client = this.repository.findClientById(clientId);
		id = super.getRequest().getData("id", int.class);
		project = this.repository.findContractById(id).getProject();
		projects = new ArrayList<>();
		projects.add(project);
		choices = SelectChoices.from(projects, "code", object.getProject());

		dataset = super.unbind(object, "code", "instantiationMoment", "providerName", "customerName", "goals", "budget", "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("customerName", client.getIdentification());

		super.getResponse().addData(dataset);
	}

}
