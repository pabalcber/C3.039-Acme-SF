
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Claim {

	@NotBlank
	@Pattern(regexp = "C-[0-9]{4}")
	private String	code;

	@Past
	private Date	instantiationMoment;

	@NotBlank
	@Size(max = 76)
	private String	heading;

	@NotBlank
	@Size(max = 101)
	private String	description;

	@NotBlank
	@Size(max = 101)
	private String	department;

	@Email
	private String	emailAddress;

	private String	link;


	// Constructors, Getters and Setters
	// Constructor
	public Claim(final String code, final Date instantiationMoment, final String heading, final String description, final String department, final String emailAddress, final String link) {
		this.code = code;
		this.instantiationMoment = instantiationMoment;
		this.heading = heading;
		this.description = description;
		this.department = department;
		this.emailAddress = emailAddress;
		this.link = link;
	}
	// Getters and Setters
	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public Date getInstantiationMoment() {
		return this.instantiationMoment;
	}

	public void setInstantiationMoment(final Date instantiationMoment) {
		this.instantiationMoment = instantiationMoment;
	}

	public String getHeading() {
		return this.heading;
	}

	public void setHeading(final String heading) {
		this.heading = heading;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(final String department) {
		this.department = department;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	// toString method
	@Override
	public String toString() {
		return "Claim{" + "code='" + this.code + '\'' + ", instantiationMoment=" + this.instantiationMoment + ", heading='" + this.heading + '\'' + ", description='" + this.description + '\'' + ", department='" + this.department + '\'' + ", emailAddress='"
			+ this.emailAddress + '\'' + ", link='" + this.link + '\'' + '}';
	}
}
