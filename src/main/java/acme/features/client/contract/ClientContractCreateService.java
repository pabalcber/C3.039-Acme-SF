
package acme.features.client.contract;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contracts.Contract;
import acme.entities.projects.Project;
import acme.roles.clients.Client;

@Service
public class ClientContractCreateService extends AbstractService<Client, Contract> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientContractRepository	repository;

	// AbstractService interface ----------------------------------------------

	private static String				budget	= "budget";


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Contract object;
		Client client;
		Date moment;

		moment = MomentHelper.getCurrentMoment();
		client = this.repository.findClientById(super.getRequest().getPrincipal().getActiveRoleId());

		object = new Contract();
		object.setInstantiationMoment(moment);
		object.setDraftMode(true);
		object.setClient(client);

		super.getBuffer().addData(object);
	}


	private static String invalidObject = "Invalid object: ";


	@Override
	public void bind(final Contract object) {
		if (object == null)
			throw new IllegalArgumentException(ClientContractCreateService.invalidObject + object);

		int projectId;
		Project project;
		Date moment;

		moment = MomentHelper.getCurrentMoment();
		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);

		super.bind(object, "code", "providerName", "customerName", "goals", ClientContractCreateService.budget);
		object.setProject(project);
		object.setInstantiationMoment(moment);
	}

	@Override
	public void validate(final Contract object) {
		if (object == null)
			throw new IllegalArgumentException(ClientContractCreateService.invalidObject + object);

		if (!super.getBuffer().getErrors().hasErrors("project")) {
			Project project = object.getProject();
			super.state(!project.isDraftMode(), "project", "client.contract.form.error.non-pblished-project");
		}
		this.validateUniqueCode(object);
		this.validateBudget(object);
	}

	@Override
	public void perform(final Contract object) {
		if (object == null)
			throw new IllegalArgumentException(ClientContractCreateService.invalidObject + object);

		Date moment;

		moment = MomentHelper.getCurrentMoment();

		object.setInstantiationMoment(moment);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Contract object) {
		if (object == null)
			throw new IllegalArgumentException(ClientContractCreateService.invalidObject + object);

		Collection<Project> projects;
		SelectChoices choices;
		Dataset dataset;

		projects = this.repository.findManyProjects();
		choices = SelectChoices.from(projects, "code", object.getProject());

		dataset = super.unbind(object, "code", "providerName", "customerName", "goals", ClientContractCreateService.budget, "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

	// Ancillary methods ------------------------------------------------------

	private void validateUniqueCode(final Contract object) {
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Contract existing = this.repository.findOneContractByCode(object.getCode());
			super.state(existing == null, "code", "client.contract.form.error.duplicated");
		}
	}

	private void validateBudget(final Contract object) {
		if (!super.getBuffer().getErrors().hasErrors(ClientContractCreateService.budget)) {
			Money b = object.getBudget();
			Project project = object.getProject();
			Set<String> validCurrencies = Set.of("USD", "EUR", "GBP");

			if (b == null) {
				super.state(false, ClientContractCreateService.budget, "client.contract.form.error.budget-cannot-be-null");
				return;
			}

			super.state(b.getAmount() >= 0, ClientContractCreateService.budget, "client.contract.form.error.negative-budget");

			if (project != null) {
				Money projectCost = project.getCost();
				if (!validCurrencies.contains(b.getCurrency()))
					super.state(false, "budget", "client.contract.form.error.invalid-currency");

				if (!b.getCurrency().equals(projectCost.getCurrency()))
					super.state(false, ClientContractCreateService.budget, "client.contract.form.error.different-currency");

				if (b.getAmount() > projectCost.getAmount())
					super.state(false, ClientContractCreateService.budget, "client.contract.form.error.budget-exceeds-project-cost");
			}
		}
	}

}
