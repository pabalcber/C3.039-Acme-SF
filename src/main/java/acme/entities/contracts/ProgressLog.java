
package acme.entities.contracts;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProgressLog extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^PG-[A-Z]{1,2}-\\d{4}$", message = "PG-[A-Z]{1,2}-[0-9]{4}")
	private String				recordId;

	@Positive
	@Max(100)
	private double				completeness;

	@NotBlank
	@Length(min = 1, max = 101)
	private String				comment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				registrationMoment;

	@NotBlank
	@Length(min = 1, max = 76)
	private String				responsiblePerson;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.comment, this.completeness, this.contract, this.recordId, this.registrationMoment, this.responsiblePerson);
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
		ProgressLog other = (ProgressLog) obj;
		return Objects.equals(this.comment, other.comment) && Double.doubleToLongBits(this.completeness) == Double.doubleToLongBits(other.completeness) && Objects.equals(this.contract, other.contract) && Objects.equals(this.recordId, other.recordId)
			&& Objects.equals(this.registrationMoment, other.registrationMoment) && Objects.equals(this.responsiblePerson, other.responsiblePerson);
	}

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	@OneToMany()
	private Contract contract;
}
