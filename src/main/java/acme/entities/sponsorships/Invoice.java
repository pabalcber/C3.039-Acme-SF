
package acme.entities.sponsorships;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice extends AbstractEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Attributes
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "PG-[A-Z]{1,2}-//d{4}", message = "El recordId debe seguir el patrón 'PG-[A-Z]{1,2}-[0-9]{4}'")
	private String				code;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				registrationTime;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date				dueDate;

	@NotNull
	@PositiveOrZero
	private int					quantity;

	@NotNull
	@PositiveOrZero
	private double				tax;

	@NotNull
	private double				totalAmount;

	@URL
	@Length(max = 255)
	private String				link;

	// Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsorship			sponsorship;

}
