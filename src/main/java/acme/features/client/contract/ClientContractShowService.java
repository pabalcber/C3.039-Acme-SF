
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
		contractId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(contractId);
		status = super.getRequest().getPrincipal().hasRole(contract.getClient()) || contract != null && !contract.isDraftMode();
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
