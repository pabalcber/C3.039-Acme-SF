
package acme.roles.clients;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Client extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "CLI-\\d{4}", message = "CLI-[0-9]{4}")
	private String				identification;

	@NotBlank
	@Length(min = 1, max = 76)
	private String				companyName;

	@NotNull
	private ClientType			type;

	@NotBlank
	@Email
	@Column(unique = true)
	private String				email;

	@URL
	@Length(max = 255)
	private String				furtherInformation;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.companyName, this.email, this.furtherInformation, this.identification, this.type);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(this.companyName, other.companyName) && Objects.equals(this.email, other.email) && Objects.equals(this.furtherInformation, other.furtherInformation) && Objects.equals(this.identification, other.identification)
			&& this.type == other.type;
	}

}
