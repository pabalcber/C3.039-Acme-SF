
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Developer extends AbstractEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@NotNull
	@Max(75)
	private String				degree;

	@NotBlank
	@NotNull
	@Max(100)
	private String				specialisation;

	@NotBlank
	@Max(100)
	@NotNull
	private String				skills;

	@Email
	@NotBlank
	@NotNull
	@Max(255)
	private String				email;

	@Max(255)
	private String				furtherInformationLink;
}
