
package acme.features.client.progresslog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.clients.Client;

@Service
public class ClientProgressLogUpdateService extends AbstractService<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientProgressLogRepository	repository;

	// AbstractService interface ----------------------------------------------

	private static String				responsiblePerson	= "responsiblePerson";


	@Override
	public void authorise() {
		boolean status;
		int progressLogId;
		Contract contract;

		progressLogId = super.getRequest().getData("id", int.class);
		contract = this.repository.findOneContractByProgressLogId(progressLogId);
		status = contract != null && contract.isDraftMode() && super.getRequest().getPrincipal().hasRole(contract.getClient());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProgressLogById(id);

		super.getBuffer().addData(object);
	}


	private static String invalidObject = "Invalid object: ";


	@Override
	public void bind(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogUpdateService.invalidObject + object);

		super.bind(object, "recordId", "completeness", "comment", "registrationMoment", ClientProgressLogUpdateService.responsiblePerson);
	}

	@Override
	public void validate(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogUpdateService.invalidObject + object);
	}

	@Override
	public void perform(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogUpdateService.invalidObject + object);

		int id = super.getRequest().getData("id", int.class);
		ProgressLog pl = this.repository.findOneProgressLogById(id);
		Client client = object.getContract().getClient();

		object.setResponsiblePerson(client.getIdentification());
		object.setRecordId(pl.getRecordId());
		this.repository.save(object);
	}

	@Override
	public void unbind(final ProgressLog object) {

		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogUpdateService.invalidObject + object);

		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", ClientProgressLogUpdateService.responsiblePerson);
		dataset.put("masterId", object.getContract().getId());
		dataset.put("draftMode", object.getContract().isDraftMode());
		dataset.put("contract", object.getContract().getCode());
		dataset.put(ClientProgressLogUpdateService.responsiblePerson, object.getContract().getClient().getIdentification());
	}

}
