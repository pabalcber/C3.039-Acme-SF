
package acme.entities.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperDashboardService {

	private final TrainingRepository	trainingRepository;
	private final SessionRepository		sessionRepository;


	@Autowired
	public DeveloperDashboardService(final TrainingRepository trainingRepository, final SessionRepository sessionRepository) {
		this.trainingRepository = trainingRepository;
		this.sessionRepository = sessionRepository;
	}

	public long getTotalTrainingModulesWithUpdateMoment() {
		return this.trainingRepository.countByUpdateMomentNotNull();
	}

	public long getTotalTrainingSessionsWithLink() {
		return this.sessionRepository.countByFurtherInformationLinkIsNotNull();
	}

	public double getAverageTrainingModuleTime() {
		Iterable<Training> trainings = this.trainingRepository.findAll();
		int count = 0;
		long totalTime = 0;
		for (Training training : trainings)
			if (training.getEstimatedTotalTime() != null) {
				totalTime += training.getEstimatedTotalTime();
				count++;
			}
		return count > 0 ? (double) totalTime / count : 0;
	}

	public double getStandardDeviationTrainingModuleTime() {
		Iterable<Training> trainings = this.trainingRepository.findAll();
		int count = 0;
		long totalTime = 0;
		long squaredTotalTime = 0;
		for (Training training : trainings)
			if (training.getEstimatedTotalTime() != null) {
				totalTime += training.getEstimatedTotalTime();
				squaredTotalTime += Math.pow(training.getEstimatedTotalTime(), 2);
				count++;
			}
		if (count > 0) {
			double mean = (double) totalTime / count;
			return Math.sqrt((double) squaredTotalTime / count - Math.pow(mean, 2));
		} else
			return 0;
	}

	public long getMinimumTrainingModuleTime() {
		Iterable<Training> trainings = this.trainingRepository.findAll();
		long minTime = Long.MAX_VALUE;
		for (Training training : trainings)
			if (training.getEstimatedTotalTime() != null && training.getEstimatedTotalTime() < minTime)
				minTime = training.getEstimatedTotalTime();
		return minTime != Long.MAX_VALUE ? minTime : 0;
	}

	public long getMaximumTrainingModuleTime() {
		Iterable<Training> trainings = this.trainingRepository.findAll();
		long maxTime = Long.MIN_VALUE;
		for (Training training : trainings)
			if (training.getEstimatedTotalTime() != null && training.getEstimatedTotalTime() > maxTime)
				maxTime = training.getEstimatedTotalTime();
		return maxTime != Long.MIN_VALUE ? maxTime : 0;
	}
}
