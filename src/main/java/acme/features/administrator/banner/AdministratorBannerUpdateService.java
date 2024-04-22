
package acme.features.administrator.banner;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.banners.Banner;

@Service
public class AdministratorBannerUpdateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Banner banner;

		masterId = super.getRequest().getData("id", int.class);
		banner = this.repository.findOneBannerById(masterId);
		status = banner != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Banner object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneBannerById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "target", "moment", "slogan", "picture", "displayStartMoment", "displayEndMoment");
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

		if (object.getDisplayStartMoment() != null && object.getMoment() != null) {
			boolean isDisplayStartMomentAfterMoment = MomentHelper.isAfter(object.getDisplayStartMoment(), object.getMoment());
			super.state(isDisplayStartMomentAfterMoment, "displayStartMoment", "administrator.banner.form.error.start-display-must-be-after-moment");
		}

		if (object.getDisplayStartMoment() != null && object.getDisplayEndMoment() != null) {

			Date oneWeekLater = MomentHelper.deltaFromMoment(object.getDisplayStartMoment(), 1, ChronoUnit.WEEKS);

			boolean isDisplayEndMomentAtLeastOneWeekLater = MomentHelper.isAfterOrEqual(object.getDisplayEndMoment(), oneWeekLater);
			super.state(isDisplayEndMomentAtLeastOneWeekLater, "displayEndMoment", "administrator.banner.form.error.display-duration-must-be-at-least-one-week");
		}
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "target", "moment", "slogan", "picture", "displayStartMoment", "displayEndMoment");
		dataset.put("confirmation", false);

		super.getResponse().addData(dataset);
	}
}
