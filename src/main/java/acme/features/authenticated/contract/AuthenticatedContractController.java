
package acme.features.authenticated.contract;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Authenticated;
import acme.entities.contracts.Contract;

@Controller
public class AuthenticatedContractController extends AbstractController<Authenticated, Contract> {

	// Internal State ---------------------------------------------------------------------------------------------------------------------------------------

	@Autowired
	private AuthenticatedContractListAllService	listAllService;

	@Autowired
	private AuthenticatedContractShowService	showService;

	// Constructors -----------------------------------------------------------------------------------------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addCustomCommand("list-all", "list", this.listAllService);
	}

}
