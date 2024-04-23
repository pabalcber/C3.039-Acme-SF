
package acme.features.client.progresslog;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.clients.Client;

@Service
public class ClientProgressLogListService extends AbstractService<Client, ProgressLog> {

	// Internal State ---------------------------------------------------------------------------------------------------------------------------------------

	@Autowired
	private ClientProgressLogRepository	repository;

	// AbstractService interface ----------------------------------------------------------------------------------------------------------------------------

	private static String				id	= "masterId";


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Contract contract;

		masterId = super.getRequest().getData(ClientProgressLogListService.id, int.class);
		contract = this.repository.findOneContractById(masterId);
		status = contract != null && (!contract.isDraftMode() || super.getRequest().getPrincipal().hasRole(contract.getClient()));

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<ProgressLog> objects;
		int masterId;

		masterId = super.getRequest().getData(ClientProgressLogListService.id, int.class);
		objects = this.repository.findManyProgressLogsByMasterId(masterId);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException("Invalid object: " + object);

		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "contract");

		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<ProgressLog> objects) {
		if (objects == null)
			throw new IllegalArgumentException("Invalid object: " + objects);

		int masterId;
		Contract contract;
		final boolean showCreate;

		masterId = super.getRequest().getData(ClientProgressLogListService.id, int.class);
		contract = this.repository.findOneContractById(masterId);
		showCreate = contract.isDraftMode() && super.getRequest().getPrincipal().hasRole(contract.getClient());

		super.getResponse().addGlobal(ClientProgressLogListService.id, masterId);
		super.getResponse().addGlobal("showCreate", showCreate);
	}

}
