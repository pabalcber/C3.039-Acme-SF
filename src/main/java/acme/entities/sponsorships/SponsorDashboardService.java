
package acme.entities.sponsorships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorDashboardService {

	@Autowired
	private InvoiceRepository		invoiceRepository;

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;


	public long getTotalInvoicesWithTaxLessThanOrEqual(final double taxPercentage) {
		return this.invoiceRepository.countByTaxLessThanOrEqual(taxPercentage);
	}

	public long getTotalSponsorshipsWithLink() {
		return this.sponsorshipRepository.countByFurtherInformationLinkIsNotNull();
	}

	public double getAverageAmountOfSponsorships() {
		return this.sponsorshipRepository.findAverageAmount();
	}

	public double getStandardDeviationOfSponsorshipsAmount() {
		return this.sponsorshipRepository.findStandardDeviationAmount();
	}

	public double getMinimumAmountOfSponsorships() {
		return this.sponsorshipRepository.findMinimumAmount();
	}

	public double getMaximumAmountOfSponsorships() {
		return this.sponsorshipRepository.findMaximumAmount();
	}

	public double getAverageQuantityOfInvoices() {
		return this.invoiceRepository.findAverageQuantity();
	}

	public double getStandardDeviationOfInvoicesQuantity() {
		return this.invoiceRepository.findStandardDeviationQuantity();
	}

	public int getMinimumQuantityOfInvoices() {
		return this.invoiceRepository.findMinimumQuantity();
	}

	public int getMaximumQuantityOfInvoices() {
		return this.invoiceRepository.findMaximumQuantity();
	}
}
