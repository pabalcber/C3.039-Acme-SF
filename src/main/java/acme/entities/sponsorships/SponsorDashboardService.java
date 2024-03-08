
package acme.entities.sponsorships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorDashboardService {

	@Autowired
	private InvoiceRepository		invoiceRepository;

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;


	//total number of invoices with a tax less than or equal to 21.00%
	public long getTotalInvoicesWithTaxLessThanOrEqual(final double taxPercentage) {
		return this.invoiceRepository.countByTaxLessThanOrEqual(taxPercentage);
	}
	//total number of sponsorships with a link
	public long getTotalSponsorshipsWithLink() {
		return this.sponsorshipRepository.countByFurtherInformationLinkIsNotNull();
	}
	//average
	public double getAverageAmountOfSponsorships() {
		return this.sponsorshipRepository.findAverageAmount();
	}
	//deviation
	public double getStandardDeviationOfSponsorshipsAmount() {
		return this.sponsorshipRepository.findStandardDeviationAmount();
	}
	//minimum
	public double getMinimumAmountOfSponsorships() {
		return this.sponsorshipRepository.findMinimumAmount();
	}
	//maximum 
	public double getMaximumAmountOfSponsorships() {
		return this.sponsorshipRepository.findMaximumAmount();
	}
	//average
	public double getAverageQuantityOfInvoices() {
		return this.invoiceRepository.findAverageQuantity();
	}
	//deviation
	public double getStandardDeviationOfInvoicesQuantity() {
		return this.invoiceRepository.findStandardDeviationQuantity();
	}
	//minimum
	public int getMinimumQuantityOfInvoices() {
		return this.invoiceRepository.findMinimumQuantity();
	}
	//maximum 
	public int getMaximumQuantityOfInvoices() {
		return this.invoiceRepository.findMaximumQuantity();
	}
}
