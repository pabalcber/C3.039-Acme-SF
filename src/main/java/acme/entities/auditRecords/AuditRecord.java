
package acme.entities.auditRecords;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.entities.codeAudits.CodeAudit;
import acme.roles.Auditor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Unique
	@Pattern(regexp = "^[A-Z]{1,3}-[0-9]{3}$")
	@NotNull
	private String				code;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				startPeriod;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				endPeriod;

	@Pattern(regexp = "A\\+|A|B|C|F|F-")
	private String				mark;

	@URL
	private String				link;

	protected boolean			draftMode;

	// Derived attributes -----------------------------------------------------


	public boolean validatePeriod() {
		boolean res = true;
		if (this.startPeriod != null && this.endPeriod != null) {
			long differenceInHours = (this.endPeriod.getTime() - this.startPeriod.getTime()) / 3600;
			if (differenceInHours < 1)
				res = false;
		}
		return res;
	}

	// Relationships ----------------------------------------------------------


	@ManyToOne
	protected CodeAudit	codeAudit;

	@NotNull
	@ManyToOne(optional = false)
	protected Auditor	auditor;

}
