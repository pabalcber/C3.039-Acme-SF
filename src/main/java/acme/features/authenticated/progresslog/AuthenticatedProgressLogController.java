
package acme.features.authenticated.progresslog;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Authenticated;
import acme.entities.contracts.ProgressLog;

@Controller
public class AuthenticatedProgressLogController extends AbstractController<Authenticated, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedProgressLogListService	listService;

	@Autowired
	private AuthenticatedProgressLogShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
