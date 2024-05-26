
package acme.entities.objectives;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.entities.projects.Project;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Objective extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationMoment;

	@NotBlank
	@Length(max = 76)
	@NotNull
	private String				title;

	@NotBlank
	@Length(max = 101)
	@NotNull
	private String				description;

	@NotNull
	private PriorityType		priority;

	@NotNull
	private Boolean				status;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startDuration;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				finishDuration;

	@URL
	@Length(max = 255)
	private String				furtherInformationLink;

	// Relationships ----------------------------------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project				project;

}
