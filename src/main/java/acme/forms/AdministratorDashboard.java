
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	int							totalAuditors;
	int							totalConsumers;
	int							totalClients;
	int							totalManagers;
	int							totalProviders;
	double						ratioNoticesWithEmailAndLink;
	double						ratioCriticalObjectives;
	double						ratioNonCriticalObjectives;
	double						avgRisksValue;
	double						deviationRisksValue;
	double						minimumRisksValue;
	double						maximumRisksValue;
	double						avgLast10WeeksClaims;
	double						deviationLast10WeeksClaims;
	double						minimumLast10WeeksClaims;
	double						maximumLast10WeeksClaims;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
