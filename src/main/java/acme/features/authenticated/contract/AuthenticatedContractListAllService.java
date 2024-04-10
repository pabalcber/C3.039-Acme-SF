
package acme.features.authenticated.contract;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;

@Service
public class AuthenticatedContractListAllService extends AbstractService<Authenticated, Contract> {

	// Internal State ---------------------------------------------------------------------------------------------------------------------------------------

	@Autowired
	private AuthenticatedContractRepository repository;

	// AbstractService interface ----------------------------------------------------------------------------------------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Contract> objects;

		objects = this.repository.findAllPublishedContracts();

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "providerName", "customerName", "budget", "project");

		super.getResponse().addData(dataset);
	}

}
