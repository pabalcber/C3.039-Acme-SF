
package acme.entities.dashboards;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.client.data.AbstractEntity;
// import acme.roles.Administrator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AdministratorDashboard extends AbstractEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Attributes
	@NotNull
	private Integer				totalPrincipalsAdmins;

	@NotNull
	private Integer				totalPrincipalsManagers;

	@NotNull
	private Integer				totalPrincipalsEmployees;

	@NotNull
	private Double				noticeEmailLinkRatio;

	@NotNull
	private Double				criticalObjectivesRatio;

	@NotNull
	private Double				nonCriticalObjectivesRatio;

	@NotNull
	private Double				averageRiskValue;

	@NotNull
	private Double				minRiskValue;

	@NotNull
	private Double				maxRiskValue;

	@NotNull
	private Double				stdDevRiskValue;

	@NotNull
	private Double				averageClaimsPosted;

	@NotNull
	private Double				minClaimsPosted;

	@NotNull
	private Double				maxClaimsPosted;

	@NotNull
	private Double				stdDevClaimsPosted;

	// Relationships
	/*
	 * @Valid
	 * 
	 * @OneToOne
	 * private Administrator administrator;
	 */
}
