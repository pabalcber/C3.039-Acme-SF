
package acme.entities.sponsorships;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

	long countByTaxLessThanOrEqual(double taxPercentage);

	@Query("SELECT COUNT(i) FROM Invoice i WHERE i.furtherInformationLink IS NOT NULL")
	long countByFurtherInformationLinkIsNotNull();

	@Query("SELECT AVG(s.amount) FROM Invoice s")
	double findAverageAmount();

	@Query("SELECT STDDEV(s.amount) FROM Invoice s")
	double findStandardDeviationAmount();

	@Query("SELECT MIN(s.amount) FROM Invoice s")
	double findMinimumAmount();

	@Query("SELECT MAX(s.amount) FROM Invoice s")
	double findMaximumAmount();

	@Query("SELECT AVG(i.quantity) FROM Invoice i")
	double findAverageQuantity();

	@Query("SELECT STDDEV(i.quantity) FROM Invoice i")
	double findStandardDeviationQuantity();

	@Query("SELECT MIN(i.quantity) FROM Invoice i")
	int findMinimumQuantity();

	@Query("SELECT MAX(i.quantity) FROM Invoice i")
	int findMaximumQuantity();
}
