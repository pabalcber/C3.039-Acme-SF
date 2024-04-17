
package acme.entities.contracts;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
import acme.entities.projects.Project;
import acme.roles.clients.Client;
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
	@Pattern(regexp = "^[A-Z]{1,3}-\\d{3}$", message = "[A-Z]{1,3}-[0-9]{3}")
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
	@Length(min = 1, max = 101)
	private String				goals;

	@Valid
	private Money				budget;

	private boolean				draftMode;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.budget, this.client, this.code, this.customerName, this.draftMode, this.goals, this.instantiationMoment, this.project, this.providerName);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		return Objects.equals(this.budget, other.budget) && Objects.equals(this.client, other.client) && Objects.equals(this.code, other.code) && Objects.equals(this.customerName, other.customerName) && this.draftMode == other.draftMode
			&& Objects.equals(this.goals, other.goals) && Objects.equals(this.instantiationMoment, other.instantiationMoment) && Objects.equals(this.project, other.project) && Objects.equals(this.providerName, other.providerName);
	}

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Client	client;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project	project;
}
