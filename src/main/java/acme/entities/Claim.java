
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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


	public Claim() {

	}
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

	// toString method
	@Override
	public String toString() {
		return "Claim{" + "code='" + this.code + '\'' + ", instantiationMoment=" + this.instantiationMoment + ", heading='" + this.heading + '\'' + ", description='" + this.description + '\'' + ", department='" + this.department + '\'' + ", emailAddress='"
			+ this.emailAddress + '\'' + ", link='" + this.link + '\'' + '}';
	}
}
