
package acme.entities.contracts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProgressLog extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "PG-[A-Z]{1,2}-[0-9]{4}", message = "El recordId debe seguir el patrón 'PG-[A-Z]{1,2}-[0-9]{4}'")
	private String				recordId;

	@Positive
	private Double				completeness;

	@NotBlank
	@Length(min = 1, max = 101)
	private String				comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				registrationMoment;

	@NotBlank
	@Length(min = 1, max = 76)
	private String				responsiblePerson;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	@OneToMany()
	private Contract			contract;
}