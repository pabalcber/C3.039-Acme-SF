
package acme.forms;

import java.util.Map;

import acme.client.data.AbstractForm;
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
	Map<String, Double>			averageProjectCosts;
	Map<String, Double>			deviationProjectCosts;
	Map<String, Double>			minimumProjectCosts;
	Map<String, Double>			maximumProjectCosts;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
