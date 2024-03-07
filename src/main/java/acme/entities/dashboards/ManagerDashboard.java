
package acme.entities.dashboards;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import acme.roles.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ManagerDashboard extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotNull
	@Transient
	private int					mustUserStories;
	@NotNull
	@Transient
	private int					shouldUserStories;
	@NotNull
	@Transient
	private int					couldUserStories;
	@NotNull
	@Transient
	private int					wontUserStories;
	@NotNull
	@Transient
	private Double				averageEstimatedCost;
	@NotNull
	@Transient
	private Double				deviationEstimatedCost;
	@NotNull
	@Transient
	private Double				minimumEstimatedCost;
	@NotNull
	@Transient
	private Double				maximumEstimatedCost;
	@NotNull
	@Transient
	private Money				averageProjectCosts;
	@NotNull
	@Transient
	private Money				deviationProjectCosts;
	@NotNull
	@Transient
	private Money				minimumProjectCosts;
	@NotNull
	@Transient
	private Money				maximumProjectCosts;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	@Valid
	@OneToOne
	private Manager				manager;
}
