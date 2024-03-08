
package acme.entities.risks;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Risk extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "R-[0-9]{3}", message = "")
	@Length(max = 255)
	private String				reference;
	@PastOrPresent
	@NotNull
	private Date				identificationDate;
	@Range(min = 0)
	@NotNull
	private int					impact;
	@NotNull
	@Range(min = 0, max = 1)
	private Double				probability;
	@NotBlank
	@Length(max = 100)
	private String				description;
	@URL
	@Length(max = 255)
	private String				optionalLink;
	// Derived attributes -----------------------------------------------------
	//@Transient
	//private Double				value				= this.impact * this.probability;
	// Relationships ----------------------------------------------------------
}
