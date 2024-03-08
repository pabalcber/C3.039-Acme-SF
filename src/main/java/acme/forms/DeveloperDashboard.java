
package acme.forms;

import java.util.Date;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperDashboard extends AbstractForm {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Attributes
	Integer						totalTrainingModules;

	Date						trainingModulesUpdateMoment;

	Integer						totalTrainingSessions;

	String						trainingSessionsLink;

	Double						averageTrainingModuleTime;

	Double						deviationTrainingModuleTime;

	Integer						minimumTrainingModuleTime;

	Integer						maximumTrainingModuleTime;

}
