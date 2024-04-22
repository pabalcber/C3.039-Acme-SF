
package acme.forms;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDashboard extends AbstractForm {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	int							mustUserStories;
	int							shouldUserStories;
	int							couldUserStories;
	int							wontUserStories;
	Double						averageEstimatedCost;
	Double						deviationEstimatedCost;
	Double						minimumEstimatedCost;
	Double						maximumEstimatedCost;
	Money						averageProjectCostsEUR;
	Money						averageProjectCostsGBP;
	Money						averageProjectCostsUSD;
	Money						deviationProjectCostsEUR;
	Money						deviationProjectCostsGBP;
	Money						deviationProjectCostsUSD;
	Money						minimumProjectCostsEUR;
	Money						minimumProjectCostsGBP;
	Money						minimumProjectCostsUSD;
	Money						maximumProjectCostsEUR;
	Money						maximumProjectCostsGBP;
	Money						maximumProjectCostsUSD;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
