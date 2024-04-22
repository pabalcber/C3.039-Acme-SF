
package acme.features.administrator.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Administrator;
import acme.entities.banners.Banner;

@Controller
public class AdministratorBannerController extends AbstractController<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerListAllService	listAllService;

	@Autowired
	private AdministratorBannerShowService		showService;

	@Autowired
	private AdministratorBannerCreateService	createService;

	@Autowired
	private AdministratorBannerDeleteService	deleteService;

	@Autowired
	private AdministratorBannerUpdateService	updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addCustomCommand("list-all", "list", this.listAllService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("update", this.updateService);
	}

}
