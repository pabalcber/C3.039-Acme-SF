
package acme.entities.sponsorships;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SponsorDashboards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	private long	totalInvoicesWithTaxLessThanOrEqual;
	private long	totalSponsorshipsWithLink;
	private double	averageAmountOfSponsorships;
	private double	standardDeviationAmountOfSponsorships;
	private double	minimumAmountOfSponsorships;
	private double	maximumAmountOfSponsorships;
	private double	averageQuantityOfInvoices;
	private double	standardDeviationQuantityOfInvoices;
	private int		minimumQuantityOfInvoices;
	private int		maximumQuantityOfInvoices;
}
