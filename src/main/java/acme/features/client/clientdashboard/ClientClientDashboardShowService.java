
package acme.features.client.clientdashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.clients.Client;

@Service
public class ClientClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientClientDashboardRepository repository;


	// AbstractService interface ----------------------------------------------
	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		int clientId = super.getRequest().getPrincipal().getActiveRoleId();

		ClientDashboard dashboard = new ClientDashboard();

		this.loadTotalProgressLogs(clientId, dashboard);
		this.loadAverageBudgets(clientId, dashboard);
		this.loadDeviationBudgets(clientId, dashboard);
		this.loadMinimumBudgets(clientId, dashboard);
		this.loadMaximumBudgets(clientId, dashboard);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalProgressLogsBelow25Percent", "totalProgressLogs25To50Percent", "totalProgressLogs50To75Percent", "totalProgressLogsAbove75Percent", //
			"avgBudgetEUR", "avgBudgetGBP", "avgBudgetUSD", "deviationBudgetEUR", "deviationBudgetGBP", "deviationBudgetUSD",//
			"minimumBudgetEUR", "minimumBudgetGBP", "minimumBudgetUSD", "maximumBudgetEUR", "maximumBudgetGBP", "maximumBudgetUSD");

		super.getResponse().addData(dataset);
	}

	// Ancillary methods ------------------------------------------------------

	private void loadTotalProgressLogs(final int clientId, final ClientDashboard dashboard) {
		dashboard.setTotalProgressLogsBelow25Percent(this.repository.totalProgressLogsBelow25Percent(clientId));
		dashboard.setTotalProgressLogs25To50Percent(this.repository.totalProgressLogs25to50Percent(clientId));
		dashboard.setTotalProgressLogs50To75Percent(this.repository.totalProgressLogs50to75Percent(clientId));
		dashboard.setTotalProgressLogsAbove75Percent(this.repository.totalProgressLogsAbove75Percent(clientId));
	}

	private void loadAverageBudgets(final int clientId, final ClientDashboard dashboard) {
		dashboard.setAvgBudgetEUR(this.createMoney("EUR", this.repository.averageBudgetsInEuros(clientId)));
		dashboard.setAvgBudgetGBP(this.createMoney("GBP", this.repository.averageBudgetsInGBP(clientId)));
		dashboard.setAvgBudgetUSD(this.createMoney("USD", this.repository.averageBudgetsInUSD(clientId)));
	}

	private void loadDeviationBudgets(final int clientId, final ClientDashboard dashboard) {
		dashboard.setDeviationBudgetEUR(this.createMoney("EUR", this.repository.deviationOfBudgetsInEUR(clientId)));
		dashboard.setDeviationBudgetGBP(this.createMoney("GBP", this.repository.deviationOfBudgetsInGBP(clientId)));
		dashboard.setDeviationBudgetUSD(this.createMoney("USD", this.repository.deviationOfBudgetsInUSD(clientId)));
	}

	private void loadMinimumBudgets(final int clientId, final ClientDashboard dashboard) {
		dashboard.setMinimumBudgetEUR(this.createMoney("EUR", this.repository.minimumBudgetInEUR(clientId)));
		dashboard.setMinimumBudgetGBP(this.createMoney("GBP", this.repository.minimumBudgetInGBP(clientId)));
		dashboard.setMinimumBudgetUSD(this.createMoney("USD", this.repository.minimumBudgetInUSD(clientId)));
	}

	private void loadMaximumBudgets(final int clientId, final ClientDashboard dashboard) {
		dashboard.setMaximumBudgetEUR(this.createMoney("EUR", this.repository.maximumBudgetInEUR(clientId)));
		dashboard.setMaximumBudgetGBP(this.createMoney("GBP", this.repository.maximumBudgetInGBP(clientId)));
		dashboard.setMaximumBudgetUSD(this.createMoney("USD", this.repository.maximumBudgetInUSD(clientId)));
	}

	private Money createMoney(final String currency, final Double amount) {
		Money money = new Money();
		if (amount == null)
			money.setAmount(0.0);
		else
			money.setAmount(amount);
		money.setCurrency(currency);
		return money;
	}

}
