
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsorship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}", message = "Code must match pattern '[A-Z]{1,3}-[0-9]{3}'")
	private String	code;

	@NotNull
	private Date	moment;

	@NotNull
	private Date	duration;

	@Positive
	private double	amount;

	@NotBlank
	@Size(max = 10)
	private String	sponsorshipType;

	@Email
	private String	contactEmail;

	private String	furtherInformationLink;


	public Sponsorship() {
	}

	public Sponsorship(final String code, final Date moment, final Date duration, final double amount, final String sponsorshipType, final String contactEmail, final String furtherInformationLink) {
		this.code = code;
		this.moment = moment;
		this.duration = duration;
		this.amount = amount;
		this.sponsorshipType = sponsorshipType;
		this.contactEmail = contactEmail;
		this.furtherInformationLink = furtherInformationLink;
	}

	@Override
	public String toString() {
		return "Sponsorship [id=" + this.id + ", code=" + this.code + ", moment=" + this.moment + ", duration=" + this.duration + ", amount=" + this.amount + ", sponsorshipType=" + this.sponsorshipType + ", contactEmail=" + this.contactEmail
			+ ", furtherInformationLink=" + this.furtherInformationLink + "]";
	}
}
