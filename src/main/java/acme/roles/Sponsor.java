
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import acme.client.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsor extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes
	@NotBlank
	@Size(max = 76)
	private String				name;

	@NotBlank
	@Size(max = 101)
	private String				benefits;

	private String				optionalWebPage;

	private String				optionalEmailContact;

	// Relationships

}
