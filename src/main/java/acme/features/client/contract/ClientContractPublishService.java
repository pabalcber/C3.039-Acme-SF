
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
public class ClientContractPublishService extends AbstractService<Client, Contract> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientContractRepository	repository;

	// AbstractService interface ----------------------------------------------

	private static String				budget	= "budget";


	@Override
	public void authorise() {
		boolean status;
		int contractId;
		Contract contract;
		Client client;
		Project project;

		contractId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(contractId);
		client = contract == null ? null : contract.getClient();
		project = contract.getProject();
		status = contract != null && contract.isDraftMode() && super.getRequest().getPrincipal().hasRole(client) && !project.isDraftMode();

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

		super.bind(object, "code", "providerName", "customerName", "goals", ClientContractPublishService.budget);
		object.setProject(project);
	}

	@Override
	public void validate(final Contract object) {
		assert object != null;

		Money budgt = object.getBudget();
		Project project = object.getProject();
		Money projectCost = project.getCost();

		Double existingCombinedBudget = this.repository.combinedBudgetByContract(project.getId());
		double totalCombinedBudget = (existingCombinedBudget != null ? existingCombinedBudget : 0.0) + budgt.getAmount();
		double projectTotalCost = projectCost.getAmount();

		super.state(totalCombinedBudget <= projectTotalCost, "*", "client.contract.form.error.bad-combined-budget");

		if (!super.getBuffer().getErrors().hasErrors("project"))
			super.state(!project.isDraftMode(), "project", "client.contract.form.error.non-pblished-project");

		if (!super.getBuffer().getErrors().hasErrors("budget"))
			super.state(totalCombinedBudget <= projectTotalCost, "*", "client.contract.form.error.bad-combined-budget");

		if (!super.getBuffer().getErrors().hasErrors(ClientContractPublishService.budget)) {

			super.state(budgt.getAmount() >= 0, ClientContractPublishService.budget, "client.contract.form.error.negative-budget");

			if (!budgt.getCurrency().equals(projectCost.getCurrency()))
				super.state(false, ClientContractPublishService.budget, "client.contract.form.error.different-currency");

			if (budgt.getAmount() > projectCost.getAmount())
				super.state(false, ClientContractPublishService.budget, "client.contract.form.error.budget-exceeds-project-cost");

		}

		this.validateCurrency(object);
	}

	@Override
	public void perform(final Contract object) {
		assert object != null;

		object.setDraftMode(false);
		this.repository.save(object);
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

		dataset = super.unbind(object, "code", "providerName", "customerName", "goals", ClientContractPublishService.budget, "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

	// Ancillary methods ------------------------------------------------------

	private void validateCurrency(final Contract object) {
		if (!super.getBuffer().getErrors().hasErrors("budget")) {
			Money b = object.getBudget();
			Project project = object.getProject();
			Set<String> validCurrencies = Set.of("USD", "EUR", "GBP");

			if (project != null)
				if (!validCurrencies.contains(b.getCurrency()))
					super.state(false, "budget", "client.contract.form.error.invalid-currency");
		}
	}

}
