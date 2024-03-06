
package acme.forms;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalProgressLogsBelow25Percent;
	Integer						totalProgressLogs25To50Percent;
	Integer						totalProgressLogs50To75Percent;
	Integer						totalProgressLogsAbove75Percent;
	Money						avgBudget;
	Money						deviationBudget;
	Money						minimumBudget;
	Money						maximumBudget;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
