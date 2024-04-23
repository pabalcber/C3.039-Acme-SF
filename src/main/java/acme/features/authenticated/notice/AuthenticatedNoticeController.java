
package acme.features.authenticated.notice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Authenticated;
import acme.entities.notices.Notice;

@Controller
public class AuthenticatedNoticeController extends AbstractController<Authenticated, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedNoticeListRecentService	listRecentService;

	@Autowired
	private AuthenticatedNoticeShowService			showService;

	@Autowired
	private AuthenticatedNoticeCreateService		createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addCustomCommand("list-recent", "list", this.listRecentService);
	}

}
