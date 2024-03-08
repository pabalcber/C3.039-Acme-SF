
package acme.entities.training;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeveloperDashboard extends AbstractEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Attributes
	@NotNull
	private Integer				totalTrainingModules;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				trainingModulesUpdateMoment;

	@NotNull
	private Integer				totalTrainingSessions;

	@NotNull
	@Size(max = 255)
	@NotBlank
	private String				trainingSessionsLink;

	@NotNull
	private Double				averageTrainingModuleTime;

	@NotNull
	private Double				deviationTrainingModuleTime;

	@NotNull
	private Integer				minimumTrainingModuleTime;

	@NotNull
	private Integer				maximumTrainingModuleTime;

}
