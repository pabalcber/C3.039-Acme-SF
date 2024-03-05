
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notice extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				instantiationMoment;

	@NotBlank
	@Length(min = 1, max = 75)
	private String				title;

	@NotBlank
	@Length(min = 1, max = 75)
	@Pattern(regexp = "^\\S+-\\S+,\\s+\\S+$", message = "El autor debe tener el formato '<username>-<surname, name>'")
	private String				author;

	@NotBlank
	@Length(min = 1, max = 100)
	private String				message;

	@Email
	private String				email;

	@URL
	private String				link;

}
