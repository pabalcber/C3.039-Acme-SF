
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorDashboards extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;
	// Attributes -------------------------------------------------------------

	long						totalInvoicesWithTaxLessThanOrEqual;
	long						totalSponsorshipsWithLink;
	double						averageAmountOfSponsorships;
	double						standardDeviationAmountOfSponsorships;
	double						minimumAmountOfSponsorships;
	double						maximumAmountOfSponsorships;
	double						averageQuantityOfInvoices;
	double						standardDeviationQuantityOfInvoices;
	int							minimumQuantityOfInvoices;
	int							maximumQuantityOfInvoices;
	// Relationships ----------------------------------------------------------
}
