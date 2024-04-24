
package acme.features.manager.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		Map<String, Double> averageProjectCosts = new HashMap<>();
		Map<String, Double> deviationProjectCosts = new HashMap<>();
		Map<String, Double> minimumProjectCosts = new HashMap<>();
		Map<String, Double> maximumProjectCosts = new HashMap<>();

		mustUserStories = this.repository.priorityUserStories(managerId, Priority.MUST);
		shouldUserStories = this.repository.priorityUserStories(managerId, Priority.SHOULD);
		couldUserStories = this.repository.priorityUserStories(managerId, Priority.COULD);
		wontUserStories = this.repository.priorityUserStories(managerId, Priority.WONT);

		averageEstimatedCost = this.repository.averageEstimatedCost(managerId);
		deviationEstimatedCost = this.repository.deviationEstimatedCost(managerId);
		minimumEstimatedCost = this.repository.minimumEstimatedCost(managerId);
		maximumEstimatedCost = this.repository.maximumEstimatedCost(managerId);

		averageProjectCosts.put("EUR", this.repository.averageProjectCosts("EUR", managerId));
		averageProjectCosts.put("GBP", this.repository.averageProjectCosts("GBP", managerId));
		averageProjectCosts.put("USD", this.repository.averageProjectCosts("USD", managerId));

		deviationProjectCosts.put("EUR", this.repository.deviationProjectCosts("EUR", managerId));
		deviationProjectCosts.put("GBP", this.repository.deviationProjectCosts("GBP", managerId));
		deviationProjectCosts.put("USD", this.repository.deviationProjectCosts("USD", managerId));

		minimumProjectCosts.put("EUR", this.repository.minimumProjectCosts("EUR", managerId));
		minimumProjectCosts.put("GBP", this.repository.minimumProjectCosts("GBP", managerId));
		minimumProjectCosts.put("USD", this.repository.minimumProjectCosts("USD", managerId));

		maximumProjectCosts.put("EUR", this.repository.maximumProjectCosts("EUR", managerId));
		maximumProjectCosts.put("GBP", this.repository.maximumProjectCosts("GBP", managerId));
		maximumProjectCosts.put("USD", this.repository.maximumProjectCosts("USD", managerId));

		dashboard = new ManagerDashboard();
		dashboard.setMustUserStories(mustUserStories);
		dashboard.setShouldUserStories(shouldUserStories);
		dashboard.setCouldUserStories(couldUserStories);
		dashboard.setWontUserStories(wontUserStories);

		dashboard.setAverageEstimatedCost(averageEstimatedCost);
		dashboard.setDeviationEstimatedCost(deviationEstimatedCost);
		dashboard.setMinimumEstimatedCost(minimumEstimatedCost);
		dashboard.setMaximumEstimatedCost(maximumEstimatedCost);

		dashboard.setAverageProjectCosts(averageProjectCosts);
		dashboard.setDeviationProjectCosts(deviationProjectCosts);
		dashboard.setMinimumProjectCosts(minimumProjectCosts);
		dashboard.setMaximumProjectCosts(maximumProjectCosts);

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
			"averageProjectCosts", "deviationProjectCosts", //
			"minimumProjectCosts", "maximumProjectCosts");

		super.getResponse().addData(dataset);
	}

}
