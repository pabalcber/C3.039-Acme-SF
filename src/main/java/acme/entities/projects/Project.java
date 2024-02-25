
package acme.entities.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
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
public class Project extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "")
	private String				code;
	@NotBlank
	@Length(max = 75)
	private String				title;
	@NotBlank
	@Length(max = 100)
	private String				abstractPj;
	private String				indication;
	@Range(min = 0)
	private Double				cost;
	@URL
	private String				optionalLink;
	//system must reject every project with fatal errors

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
