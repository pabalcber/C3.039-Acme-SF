
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Objective extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// private Integer			id;

	@NotNull
	@Past
	private Date				instantiationMoment;

	@NotBlank
	@Size(max = 75)
	private String				title;

	@NotBlank
	@Size(max = 100)
	private String				description;

	@NotBlank
	private PriorityType		priority;

	@NotBlank
	private String				status;

	@NotNull
	private Date				duration;

	private String				furtherInformationLink;


	public Objective() {
	}

	public Objective(final Date instantiationMoment, final String title, final String description, final PriorityType priority, final String status, final Date duration, final String furtherInformationLink) {
		this.instantiationMoment = instantiationMoment;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.duration = duration;
		this.furtherInformationLink = furtherInformationLink;
	}

	/*
	 * @Override
	 * public String toString() {
	 * return "Objective{" + "id=" + this.id + '\'' + ", instantiationMoment=" + this.instantiationMoment + ", title='" + this.title + '\'' + ", description='" + this.description + '\'' + ", priority='" + this.priority + '\'' + ", status='" + this.status
	 * + '\'' + ", duration=" + this.duration + ", furtherInformationLink='" + this.furtherInformationLink + '\'' + '}';
	 * }
	 */
}
