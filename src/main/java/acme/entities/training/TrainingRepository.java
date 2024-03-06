
package acme.entities.training;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, Long> {

	@Query("SELECT COUNT(t) FROM Training t WHERE t.updateMoment IS NOT NULL")
	Long countTrainingModulesWithUpdateMoment();

	@Query("SELECT AVG(t.estimatedTotalTime) FROM Training t")
	Double averageTrainingModulesTime();

	@Query("SELECT MIN(t.estimatedTotalTime) FROM Training t")
	Integer minTrainingModulesTime();

	@Query("SELECT MAX(t.estimatedTotalTime) FROM Training t")
	Integer maxTrainingModulesTime();

}
