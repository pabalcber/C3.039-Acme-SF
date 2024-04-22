
package acme.features.authenticated.notice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.accounts.UserAccount;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.notices.Notice;

@Service
public class AuthenticatedNoticeCreateService extends AbstractService<Authenticated, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedNoticeRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Notice object;
		Date moment;

		String username = super.getRequest().getPrincipal().getUsername();
		int accountId = super.getRequest().getPrincipal().getAccountId();
		UserAccount userAccount = this.repository.getUserAccountById(accountId);
		String surname = userAccount.getIdentity().getSurname();
		String name = userAccount.getIdentity().getName();

		moment = MomentHelper.getCurrentMoment();

		object = new Notice();
		object.setTitle("");
		object.setInstantiationMoment(moment);
		object.setAuthor(username + " - " + surname + ", " + name);
		object.setMessage("");
		object.setEmail("");
		object.setLink("");

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Notice object) {
		assert object != null;

		super.bind(object, "title", "author", "message", "email", "link");
	}

	@Override
	public void validate(final Notice object) {
		assert object != null;

		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

		String username = super.getRequest().getPrincipal().getUsername();
		int accountId = super.getRequest().getPrincipal().getAccountId();
		UserAccount userAccount = this.repository.getUserAccountById(accountId);
		String surname = userAccount.getIdentity().getSurname();
		String name = userAccount.getIdentity().getName();

		String expectedAuthor = username + " - " + surname + ", " + name;

		String actualAuthor = object.getAuthor();

		if (!actualAuthor.equals(expectedAuthor))
			super.state(false, "author", "authenticated.notice.form.error.bad-author-format");
	}

	@Override
	public void perform(final Notice object) {
		assert object != null;

		Date moment;

		moment = MomentHelper.getCurrentMoment();
		object.setInstantiationMoment(moment);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Notice object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "title", "author", "message", "email", "link");
		dataset.put("confirmation", false);
		dataset.put("readonly", false);

		super.getResponse().addData(dataset);
	}

}
