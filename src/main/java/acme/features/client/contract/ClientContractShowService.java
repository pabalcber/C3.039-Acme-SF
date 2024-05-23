
package acme.features.client.contract;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contracts.Contract;
import acme.entities.projects.Project;
import acme.roles.clients.Client;

@Service
public class ClientContractShowService extends AbstractService<Client, Contract> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientContractRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int contractId;
		Contract contract;
		int accountId;
		int authClientId;
		int contractClientId;
		Boolean isMyContract;

		contractId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(contractId);

		accountId = super.getRequest().getPrincipal().getAccountId();
		authClientId = this.repository.findClientByAccountId(accountId).getId();
		contractClientId = contract.getClient().getId();
		isMyContract = authClientId == contractClientId;

		status = super.getRequest().getPrincipal().hasRole(contract.getClient()) || !contract.isDraftMode();
		super.getResponse().setAuthorised(status && isMyContract);
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
	public void unbind(final Contract object) {
		assert object != null;

		SelectChoices choices;
		Project project;
		Collection<Project> projects;
		int id;

		id = super.getRequest().getData("id", int.class);
		project = this.repository.findContractById(id).getProject();
		projects = new ArrayList<>();
		projects.add(project);
		choices = SelectChoices.from(projects, "code", object.getProject());

		Dataset dataset;

		dataset = super.unbind(object, "code", "project", "client", "instantiationMoment", "providerName", "customerName", "goals", "budget", "draftMode");
		dataset.put("masterId", object.getClient().getId());
		dataset.put("projects", choices);
		super.getResponse().addData(dataset);
	}

}
