
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
public class AdministratorBannerCreateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Banner object;
		Date moment;
		Date startDisplay;
		Date endDisplay;

		moment = MomentHelper.getCurrentMoment();
		startDisplay = MomentHelper.deltaFromMoment(moment, 1, ChronoUnit.DAYS);
		endDisplay = MomentHelper.deltaFromMoment(startDisplay, 1, ChronoUnit.WEEKS);

		object = new Banner();
		object.setTarget("");
		object.setMoment(moment);
		object.setSlogan("");
		object.setPicture("");
		object.setDisplayStartMoment(startDisplay);
		object.setDisplayEndMoment(endDisplay);

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

		Date moment;

		moment = MomentHelper.getCurrentMoment();
		object.setMoment(moment);
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
