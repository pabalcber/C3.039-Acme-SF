
package acme.entities.training;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

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
	@Max(255)
	@Pattern(regexp = "TS-[A-Z]{1,3}-[0-9]{3}", message = "The code must follow the pattern 'TS-[A-Z]{1,3}-[0-9]{3}'")
	@NotNull
	private String				code;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				timePeriodStart;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				timePeriodEnd;

	@NotBlank
	@NotNull
	@Max(75)
	private String				location;

	@NotBlank
	@NotNull
	@Max(75)
	private String				instructor;

	@NotBlank
	@Email
	@Max(255)
	@NotNull
	private String				mandatoryContactEmail;

	@Max(255)
	private String				furtherInformationLink;

	// Relationships ----------------------------------------------------------
	@ManyToOne
	private Training			trainingModule;
}
