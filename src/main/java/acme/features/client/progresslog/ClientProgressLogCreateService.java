
package acme.features.client.progresslog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.clients.Client;

@Service
public class ClientProgressLogCreateService extends AbstractService<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientProgressLogRepository	repository;

	// AbstractService interface ----------------------------------------------

	private static String				responsiblePerson	= "responsiblePerson";
	private static String				recordId			= "recordId";
	private static String				id					= "masterId";
	private static String				invalidObject		= "Invalid object: ";


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Contract contract;

		masterId = super.getRequest().getData(ClientProgressLogCreateService.id, int.class);
		contract = this.repository.findOneContractById(masterId);
		status = contract != null && (!contract.isDraftMode() || super.getRequest().getPrincipal().hasRole(contract.getClient()));

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog object;
		int masterId;
		Contract contract;
		String client;

		Date moment = MomentHelper.getCurrentMoment();

		masterId = super.getRequest().getData(ClientProgressLogCreateService.id, int.class);
		contract = this.repository.findOneContractById(masterId);
		client = contract.getClient().getIdentification();

		object = new ProgressLog();
		object.setRecordId("");
		object.setCompleteness(0.1);
		object.setComment("");
		object.setRegistrationMoment(moment);
		object.setResponsiblePerson(client);
		object.setContract(contract);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogCreateService.invalidObject + object);

		super.bind(object, ClientProgressLogCreateService.recordId, "completeness", "comment", "registrationMoment", ClientProgressLogCreateService.responsiblePerson, "contract");
	}

	@Override
	public void validate(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogCreateService.invalidObject + object);

		if (!super.getBuffer().getErrors().hasErrors(ClientProgressLogCreateService.recordId)) {
			ProgressLog existing;

			existing = this.repository.findOneProgressLogByRecordId(object.getRecordId());
			super.state(existing == null, ClientProgressLogCreateService.recordId, "client.progressLog.form.error.duplicated");
		}
	}

	@Override
	public void perform(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogCreateService.invalidObject + object);

		Contract contract;
		String client;
		Date moment;
		int masterId;

		masterId = super.getRequest().getData(ClientProgressLogCreateService.id, int.class);
		moment = MomentHelper.getCurrentMoment();
		contract = this.repository.findOneContractById(masterId);
		client = contract.getClient().getIdentification();

		object.setRegistrationMoment(moment);
		object.setResponsiblePerson(client);
		this.repository.save(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		if (object == null)
			throw new IllegalArgumentException(ClientProgressLogCreateService.invalidObject + object);

		Contract contract;
		String client;
		int masterId;

		masterId = super.getRequest().getData(ClientProgressLogCreateService.id, int.class);
		contract = this.repository.findOneContractById(masterId);
		client = contract.getClient().getIdentification();

		Dataset dataset;

		dataset = super.unbind(object, ClientProgressLogCreateService.recordId, "completeness", "comment", "registrationMoment", ClientProgressLogCreateService.responsiblePerson, "contract");
		dataset.put(ClientProgressLogCreateService.id, super.getRequest().getData(ClientProgressLogCreateService.id, int.class));
		dataset.put("draftMode", object.getContract().isDraftMode());
		dataset.put(ClientProgressLogCreateService.responsiblePerson, client);

		super.getResponse().addData(dataset);
	}

}
