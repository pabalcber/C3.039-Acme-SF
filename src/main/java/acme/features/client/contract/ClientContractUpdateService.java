
package acme.features.client.contract;

import java.util.Collection;
import java.util.Set;

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


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Contract contract;
		Client client;
		Project project;

		masterId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(masterId);
		project = contract.getProject();
		client = contract.getClient();
		status = contract.isDraftMode() && super.getRequest().getPrincipal().hasRole(client) && !project.isDraftMode();

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

		super.bind(object, "code", "providerName", ClientContractUpdateService.customerName, "goals", ClientContractUpdateService.budget, "project");

	}

	@Override
	public void validate(final Contract object) {
		assert object != null;

		this.validateCurrency(object);

		if (!super.getBuffer().getErrors().hasErrors(ClientContractUpdateService.budget)) {
			Money budgt = object.getBudget();
			Project project = object.getProject();

			if (budgt == null)
				super.state(false, ClientContractUpdateService.budget, "client.contract.form.error.budget-cannot-be-null");
			else {

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
	}

	@Override
	public void perform(final Contract object) {
		assert object != null;

		Contract contract;
		int id;

		id = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(id);

		object.setCode(contract.getCode());
		object.setProject(contract.getProject());
		this.repository.save(object);
	}

	private void validateCurrency(final Contract object) {
		if (!super.getBuffer().getErrors().hasErrors("budget")) {
			Money b = object.getBudget();
			Set<String> validCurrencies = Set.of("USD", "EUR", "GBP");

			if (b != null && !validCurrencies.contains(b.getCurrency()))
				super.state(false, "budget", "client.contract.form.error.invalid-currency");
		}
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;

		int clientId;
		Collection<Project> projects;
		SelectChoices choices;
		Dataset dataset;

		clientId = super.getRequest().getPrincipal().getActiveRoleId();
		projects = this.repository.findManyProjectsByClientId(clientId);
		choices = SelectChoices.from(projects, "code", object.getProject());

		dataset = super.unbind(object, "code", "instantiationMoment", "providerName", ClientContractUpdateService.customerName, "goals", ClientContractUpdateService.budget, "draftMode");
		dataset.put("project", choices.getSelected().getLabel());
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

}
