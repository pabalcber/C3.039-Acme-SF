
package acme.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@Size(max = 75)
	@Column(length = 75)
	private String				degree;

	@NotBlank
	@Size(max = 100)
	@Column(length = 100)
	private String				specialisation;

	@NotBlank
	@Size(max = 100)
	@Column(length = 100)
	private String				skills;

	@Email
	private String				email;

	@Size(max = 255)
	@Column(length = 255)
	private String				furtherInformationLink;
}
