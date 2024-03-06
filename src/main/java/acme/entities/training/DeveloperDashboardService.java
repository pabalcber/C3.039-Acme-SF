
package acme.entities.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeveloperDashboardService {

	private final TrainingRepository	trainingRepository;
	private final SessionRepository		sessionRepository;


	@Autowired
	public DeveloperDashboardService(final TrainingRepository trainingRepository, final SessionRepository sessionRepository) {
		this.trainingRepository = trainingRepository;
		this.sessionRepository = sessionRepository;
	}

	@Transactional
	public Long countTrainingModulesWithUpdateMoment() {
		return this.trainingRepository.countTrainingModulesWithUpdateMoment();
	}

	@Transactional
	public Long countSessionsWithLink() {
		return this.sessionRepository.countSessionsWithLink();
	}

	@Transactional
	public Double averageTrainingModulesTime() {
		return this.trainingRepository.averageTrainingModulesTime();
	}

	@Transactional
	public Integer minTrainingModulesTime() {
		return this.trainingRepository.minTrainingModulesTime();
	}

	@Transactional
	public Integer maxTrainingModulesTime() {
		return this.trainingRepository.maxTrainingModulesTime();
	}
}
