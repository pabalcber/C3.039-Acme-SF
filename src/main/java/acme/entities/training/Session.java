
package acme.entities.training;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "TS-[A-Z]{1,3}-[0-9]{3}", message = "El código debe seguir el patrón 'TS-[A-Z]{1,3}-[0-9]{3}'")
	private String				code;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				timePeriodStart;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				timePeriodEnd;

	@NotBlank
	@Size(max = 75)
	private String				location;

	@NotBlank
	@Size(max = 75)
	private String				instructor;

	@NotBlank
	@Email
	private String				mandatoryContactEmail;

	private String				furtherInformationLink;

	// Relationships ----------------------------------------------------------
	@ManyToOne
	private Training			trainingModule;
}
