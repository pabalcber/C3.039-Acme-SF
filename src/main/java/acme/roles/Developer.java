
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

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
	@Size(max = 75)
	private String				degree;

	@NotBlank
	@NotNull
	@Size(max = 100)
	private String				specialisation;

	@NotBlank
	@Size(max = 100)
	@NotNull
	private String				skills;

	@Email
	@NotBlank
	@NotNull
	@Size(max = 255)
	private String				email;

	@Size(max = 255)
	@URL
	private String				furtherInformationLink;
}
