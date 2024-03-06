
package acme.entities.training;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Training extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}", message = "The code must follow the pattern 'TS-[A-Z]{1,3}-[0-9]{3}'")
	@Max(255)
	@NotNull
	private String				code;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotBlank
	@Length(max = 100)
	@NotNull
	private String				details;

	@NotNull
	private difficultyLevelType	difficultyLevel;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				updateMoment;

	@NotNull
	@Max(255)
	@NotBlank
	private String				furtherInformationLink;

	@NotNull
	private Integer				estimatedTotalTime;

}
