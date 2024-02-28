
package acme.entities.contracts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contract extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 15)
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}", message = "El código debe seguir el patrón '[A-Z]{1,3}-[0-9]{3}'")
	private String				code;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				instantiationMoment;

	@NotBlank
	@Length(min = 1, max = 76)
	private String				providerName;

	@NotBlank
	@Length(min = 1, max = 76)
	private String				customerName;

	@NotBlank
	@Length(min = 1, max = 76)
	private String				goals;

	@Valid
	private Money				budget;

	// Relationships ----------------------------------------------------------

	/*
	 * @NotNull
	 * 
	 * @Valid
	 * 
	 * @ManyToOne(optional = false)
	 * private Client client;
	 */

}
