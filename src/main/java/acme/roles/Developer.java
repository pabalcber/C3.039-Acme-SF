
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
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
	@Length(max = 75)
	private String				degree;

	@NotBlank
	@NotNull
	@Length(max = 100)
	private String				specialisation;

	@NotBlank
	@Length(max = 100)
	@NotNull
	private String				skills;

	@Email
	@NotBlank
	@NotNull
	@Length(max = 255)
	private String				email;

	@Length(max = 255)
	@URL
	private String				furtherInformationLink;
}
