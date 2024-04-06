
package acme.features.client.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.clients.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		ClientDashboard dashboard;
		int totalProgressLogsBelow25Percent;
		int totalProgressLogs25To50Percent;
		int totalProgressLogs50To75Percent;
		int totalProgressLogsAbove75Percent;

		totalProgressLogsBelow25Percent = this.repository.totalProgressLogsBelow25Percent();
		totalProgressLogs25To50Percent = this.repository.totalProgressLogs25To50Percent();
		totalProgressLogs50To75Percent = this.repository.totalProgressLogs50To75Percent();
		totalProgressLogsAbove75Percent = this.repository.totalProgressLogsAbove75Percent();

		dashboard = new ClientDashboard();
		dashboard.setTotalProgressLogsBelow25Percent(totalProgressLogsBelow25Percent);
		dashboard.setTotalProgressLogs25To50Percent(totalProgressLogs25To50Percent);
		dashboard.setTotalProgressLogs50To75Percent(totalProgressLogs50To75Percent);
		dashboard.setTotalProgressLogsAbove75Percent(totalProgressLogsAbove75Percent);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalProgressLogsBelow25Percent", "totalProgressLogs25To50Percent", // 
			"totalProgressLogs50To75Percent", "totalProgressLogsAbove75Percent");

		super.getResponse().addData(dataset);
	}

}
