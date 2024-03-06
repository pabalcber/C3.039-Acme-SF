
package acme.entities.risks;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;

public class Risk extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "R-[0-9]{3}", message = "")
	private String				reference;
	@PastOrPresent
	private Date				identificationDate;
	@Range(min = 0)
	private int					impact;
	@Range(min = 0, max = 1)
	private Double				probability;
	@NotBlank
	@Length(max = 100)
	private String				description;
	@URL
	private String				optionalLink;
	// Derived attributes -----------------------------------------------------
	@Transient
	private Double				value				= this.impact * this.probability;
	// Relationships ----------------------------------------------------------
}
