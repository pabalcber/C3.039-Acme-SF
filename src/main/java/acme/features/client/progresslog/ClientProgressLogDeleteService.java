
package acme.features.client.progresslog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.clients.Client;

@Service
public class ClientProgressLogDeleteService extends AbstractService<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientProgressLogRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int progressLogId;
		Contract contract;
		int clientContractId;
		int authClientId;
		Boolean isMyContract;
		int accountId;

		progressLogId = super.getRequest().getData("id", int.class);
		contract = this.repository.findOneContractByProgressLogId(progressLogId);

		clientContractId = contract.getClient().getId();
		accountId = super.getRequest().getPrincipal().getAccountId();
		authClientId = this.repository.findClientByAccountId(accountId).getId();
		isMyContract = authClientId == clientContractId;

		status = contract != null && contract.isDraftMode() && super.getRequest().getPrincipal().hasRole(contract.getClient());

		super.getResponse().setAuthorised(status && isMyContract);
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
			throw new IllegalArgumentException(ClientProgressLogDeleteService.invalidObject + object);

		super.bind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson");
	}

	@Override
	public void validate(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogDeleteService.invalidObject + object);
	}

	@Override
	public void perform(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogDeleteService.invalidObject + object);

		this.repository.delete(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogDeleteService.invalidObject + object);

		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson");
		dataset.put("masterId", object.getContract().getId());
		dataset.put("draftMode", object.getContract().isDraftMode());

		super.getResponse().addData(dataset);
	}

}
