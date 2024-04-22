
package acme.features.manager.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.userStories.Priority;
import acme.forms.ManagerDashboard;
import acme.roles.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, ManagerDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		int managerId = super.getRequest().getPrincipal().getActiveRoleId();

		ManagerDashboard dashboard;

		int mustUserStories;
		int shouldUserStories;
		int couldUserStories;
		int wontUserStories;

		Double averageEstimatedCost;
		Double deviationEstimatedCost;
		Double minimumEstimatedCost;
		Double maximumEstimatedCost;

		Money averageProjectCostsEUR;
		Money averageProjectCostsGBP;
		Money averageProjectCostsUSD;

		Money deviationProjectCostsEUR;
		Money deviationProjectCostsGBP;
		Money deviationProjectCostsUSD;

		Money minimumProjectCostsEUR;
		Money minimumProjectCostsGBP;
		Money minimumProjectCostsUSD;

		Money maximumProjectCostsEUR;
		Money maximumProjectCostsGBP;
		Money maximumProjectCostsUSD;

		mustUserStories = this.repository.priorityUserStories(managerId, Priority.MUST);
		shouldUserStories = this.repository.priorityUserStories(managerId, Priority.SHOULD);
		couldUserStories = this.repository.priorityUserStories(managerId, Priority.COULD);
		wontUserStories = this.repository.priorityUserStories(managerId, Priority.WONT);

		averageEstimatedCost = this.repository.averageEstimatedCost(managerId);
		deviationEstimatedCost = this.repository.deviationEstimatedCost(managerId);
		minimumEstimatedCost = this.repository.minimumEstimatedCost(managerId);
		maximumEstimatedCost = this.repository.maximumEstimatedCost(managerId);

		//Map<String, Double> cambiar el trato de moneda así

		averageProjectCostsEUR = this.createMoney("EUR", this.repository.averageProjectCostsEUR(managerId));
		averageProjectCostsGBP = this.createMoney("GBP", this.repository.averageProjectCostsGBP(managerId));
		averageProjectCostsUSD = this.createMoney("USD", this.repository.averageProjectCostsUSD(managerId));

		deviationProjectCostsEUR = this.createMoney("EUR", this.repository.deviationProjectCostsEUR(managerId));
		deviationProjectCostsGBP = this.createMoney("GBP", this.repository.deviationProjectCostsGBP(managerId));
		deviationProjectCostsUSD = this.createMoney("USD", this.repository.deviationProjectCostsUSD(managerId));

		minimumProjectCostsEUR = this.createMoney("EUR", this.repository.minimumProjectCostsEUR(managerId));
		minimumProjectCostsGBP = this.createMoney("GBP", this.repository.minimumProjectCostsGBP(managerId));
		minimumProjectCostsUSD = this.createMoney("USD", this.repository.minimumProjectCostsUSD(managerId));

		maximumProjectCostsEUR = this.createMoney("EUR", this.repository.maximumProjectCostsEUR(managerId));
		maximumProjectCostsGBP = this.createMoney("GBP", this.repository.maximumProjectCostsGBP(managerId));
		maximumProjectCostsUSD = this.createMoney("USD", this.repository.maximumProjectCostsUSD(managerId));

		dashboard = new ManagerDashboard();
		dashboard.setMustUserStories(mustUserStories);
		dashboard.setShouldUserStories(shouldUserStories);
		dashboard.setCouldUserStories(couldUserStories);
		dashboard.setWontUserStories(wontUserStories);

		dashboard.setAverageEstimatedCost(averageEstimatedCost);
		dashboard.setDeviationEstimatedCost(deviationEstimatedCost);
		dashboard.setMinimumEstimatedCost(minimumEstimatedCost);
		dashboard.setMaximumEstimatedCost(maximumEstimatedCost);

		dashboard.setAverageProjectCostsEUR(averageProjectCostsEUR);
		dashboard.setAverageProjectCostsGBP(averageProjectCostsGBP);
		dashboard.setAverageProjectCostsUSD(averageProjectCostsUSD);

		dashboard.setDeviationProjectCostsEUR(deviationProjectCostsEUR);
		dashboard.setDeviationProjectCostsGBP(deviationProjectCostsGBP);
		dashboard.setDeviationProjectCostsUSD(deviationProjectCostsUSD);

		dashboard.setMinimumProjectCostsEUR(minimumProjectCostsEUR);
		dashboard.setMinimumProjectCostsGBP(minimumProjectCostsGBP);
		dashboard.setMinimumProjectCostsUSD(minimumProjectCostsUSD);

		dashboard.setMaximumProjectCostsEUR(maximumProjectCostsEUR);
		dashboard.setMaximumProjectCostsGBP(maximumProjectCostsGBP);
		dashboard.setMaximumProjectCostsUSD(maximumProjectCostsUSD);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ManagerDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"mustUserStories", "shouldUserStories", // 
			"couldUserStories", "wontUserStories", //
			"averageEstimatedCost", "deviationEstimatedCost", //
			"minimumEstimatedCost", "maximumEstimatedCost", //
			"averageProjectCostsEUR", "averageProjectCostsGBP", //
			"averageProjectCostsUSD", "deviationProjectCostsEUR", //
			"deviationProjectCostsGBP", "deviationProjectCostsUSD", //
			"minimumProjectCostsEUR", "minimumProjectCostsGBP", //
			"minimumProjectCostsUSD", "maximumProjectCostsEUR", //
			"maximumProjectCostsGBP", "maximumProjectCostsUSD");

		super.getResponse().addData(dataset);
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
