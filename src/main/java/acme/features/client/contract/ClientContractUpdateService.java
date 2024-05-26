
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
	private ClientContractRepository	repository;

	// AbstractService<Client, Contract> -------------------------------------

	private static String				budget			= "budget";
	private static String				customerName	= "customerName";
	private static String				invalidObject	= "Invalid object: ";


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
		if (object == null)
			throw new IllegalArgumentException(ClientContractUpdateService.invalidObject + object);

		super.bind(object, "code", "instantiationMoment", "providerName", ClientContractUpdateService.customerName, "goals", ClientContractUpdateService.budget);

	}

	@Override
	public void validate(final Contract object) {
		if (object == null)
			throw new IllegalArgumentException(ClientContractUpdateService.invalidObject + object);

		if (!super.getBuffer().getErrors().hasErrors(ClientContractUpdateService.budget)) {
			Money budgt = object.getBudget();
			Project project = object.getProject();

			super.state(budgt.getAmount() >= 0, ClientContractUpdateService.budget, "client.contract.form.error.negative-budget");

			if (project != null) {
				Money projectCost = project.getCost();

				if (!budgt.getCurrency().equals(projectCost.getCurrency()))
					super.state(false, ClientContractUpdateService.budget, "client.contract.form.error.different-currency");

				if (budgt.getAmount() > projectCost.getAmount())
					super.state(false, ClientContractUpdateService.budget, "client.contract.form.error.budget-exceeds-project-cost");
			}
		}
	}

	@Override
	public void perform(final Contract object) {
		if (object == null)
			throw new IllegalArgumentException(ClientContractUpdateService.invalidObject + object);

		Contract contract;
		int id;

		id = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(id);

		object.setCode(contract.getCode());
		this.repository.save(object);
	}

	@Override
	public void unbind(final Contract object) {
		if (object == null)
			throw new IllegalArgumentException(ClientContractUpdateService.invalidObject + object);

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

		dataset = super.unbind(object, "code", "instantiationMoment", "providerName", ClientContractUpdateService.customerName, "goals", ClientContractUpdateService.budget, "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put(ClientContractUpdateService.customerName, client.getIdentification());

		super.getResponse().addData(dataset);
	}

}
