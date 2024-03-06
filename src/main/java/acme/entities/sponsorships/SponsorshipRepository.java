
package acme.entities.sponsorships;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SponsorshipRepository extends CrudRepository<Sponsorship, Long> {

	@Query("SELECT COUNT(s) FROM Sponsorship s WHERE s.furtherInformationLink IS NOT NULL")
	long countByFurtherInformationLinkIsNotNull();

	@Query("SELECT AVG(s.amount) FROM Sponsorship s")
	double findAverageAmount();

	@Query("SELECT STDDEV(s.amount) FROM Sponsorship s")
	double findStandardDeviationAmount();

	@Query("SELECT MIN(s.amount) FROM Sponsorship s")
	double findMinimumAmount();

	@Query("SELECT MAX(s.amount) FROM Sponsorship s")
	double findMaximumAmount();
}
