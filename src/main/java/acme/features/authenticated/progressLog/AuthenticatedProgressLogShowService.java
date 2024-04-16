
package acme.features.authenticated.progressLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.ProgressLog;

@Service
public class AuthenticatedProgressLogShowService extends AbstractService<Authenticated, ProgressLog> {
	// Internal State ---------------------------------------------------------------------------------------------------------------------------------------

	@Autowired
	private AuthenticatedProgressLogRepository repository;


	// AbstractService interface ----------------------------------------------------------------------------------------------------------------------------
	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		ProgressLog object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProgressLogById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "contract");
		dataset.put("masterId", object.getContract().getId());
		dataset.put("draftMode", object.getContract().isDraftMode());
		dataset.put("contract", object.getContract().getCode());

		super.getResponse().addData(dataset);
	}

}
