
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
		status = !contract.isDraftMode() || super.getRequest().getPrincipal().hasRole(contract.getClient());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog object;
		int masterId;
		Contract contract;

		Date moment = MomentHelper.getCurrentMoment();

		masterId = super.getRequest().getData(ClientProgressLogCreateService.id, int.class);
		contract = this.repository.findOneContractById(masterId);

		object = new ProgressLog();
		object.setRecordId("");
		object.setComment("");
		object.setRegistrationMoment(moment);
		object.setResponsiblePerson("");
		object.setContract(contract);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProgressLog object) {
		Date moment = MomentHelper.getCurrentMoment();
		assert object != null;

		super.bind(object, ClientProgressLogCreateService.recordId, "completeness", "comment", ClientProgressLogCreateService.responsiblePerson);
		object.setRegistrationMoment(moment);
	}

	@Override
	public void validate(final ProgressLog object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors(ClientProgressLogCreateService.recordId)) {
			ProgressLog existing;

			existing = this.repository.findOneProgressLogByRecordId(object.getRecordId());
			super.state(existing == null, ClientProgressLogCreateService.recordId, "client.progressLog.form.error.duplicated");
		}
	}

	@Override
	public void perform(final ProgressLog object) {
		assert object != null;

		Date moment;

		moment = MomentHelper.getCurrentMoment();

		object.setRegistrationMoment(moment);

		this.repository.save(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, ClientProgressLogCreateService.recordId, "completeness", "comment", ClientProgressLogCreateService.responsiblePerson);
		dataset.put(ClientProgressLogCreateService.id, super.getRequest().getData(ClientProgressLogCreateService.id, int.class));
		dataset.put("draftMode", object.getContract().isDraftMode());
		dataset.put("contract", object.getContract().getCode());

		super.getResponse().addData(dataset);
	}

}
