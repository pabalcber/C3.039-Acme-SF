
package acme.entities.userStories;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserStory extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotBlank
	@Length(max = 75)
	private String				title;
	@NotBlank
	@Length(max = 100)
	private String				description;
	@Range(min = 0)
	private Double				estimatedCost;
	@NotBlank
	@Length(max = 100)
	private String				acceptanceCriteria;
	private Priority			priority;
	@URL
	private String				optionalLink;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
