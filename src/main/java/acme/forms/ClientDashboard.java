
package acme.forms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToOne;
import javax.persistence.Transient;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.clients.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {
	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	// Derived attributes -----------------------------------------------------


	@Transient
	List<ProgressLog> clientProgressLogs() {
		List<ProgressLog> clientProgressLogs = new ArrayList<>();
		List<Contract> clientContracts = this.getClient().getContracts();

		for (Contract contract : clientContracts) {
			List<ProgressLog> progressLogs = contract.getProgressLogs();
			clientProgressLogs.addAll(progressLogs);
		}

		return clientProgressLogs;
	}

	@Transient
	private Integer totalProgressLogsBelow25Percent() {
		List<ProgressLog> clientProgressLogs = this.clientProgressLogs();

		long count = clientProgressLogs.stream().filter(progressLog -> progressLog.getCompleteness() != null && progressLog.getCompleteness() <= 25.0).count();

		return (int) count;
	}

	@Transient
	private Integer totalProgressLogs25To50Percent() {
		List<ProgressLog> clientProgressLogs = this.clientProgressLogs();

		long count = clientProgressLogs.stream().filter(progressLog -> progressLog.getCompleteness() != null && progressLog.getCompleteness() > 25.0 && progressLog.getCompleteness() <= 50.0).count();

		return (int) count;
	}

	@Transient
	private Integer totalProgressLogs50To75Percent() {
		List<ProgressLog> clientProgressLogs = this.clientProgressLogs();

		long count = clientProgressLogs.stream().filter(progressLog -> progressLog.getCompleteness() != null && progressLog.getCompleteness() > 50.0 && progressLog.getCompleteness() <= 75.0).count();

		return (int) count;
	}

	@Transient
	private Integer totalProgressLogsAbove75Percent() {
		List<ProgressLog> clientProgressLogs = this.clientProgressLogs();

		long count = clientProgressLogs.stream().filter(progressLog -> progressLog.getCompleteness() != null && progressLog.getCompleteness() > 75.0).count();

		return (int) count;
	}

	@Transient
	private Double avgBudget() {
		double totalBudget = 0.;
		double numContracts = 0.;
		List<Contract> clientContracts = this.getClient().getContracts();

		for (Contract contract : clientContracts) {
			Money budget = contract.getBudget();
			if (budget != null) {
				totalBudget += budget.getAmount();
				numContracts++;
			}
		}

		return numContracts > 0 ? totalBudget / numContracts : 0;
	}

	/*
	 * Desviacion Estandar= sqrt(sum((xi-x')^2)/n)
	 * 
	 * Donde: xi son los valores individuales del presupuesto.
	 * x' es el promedio del presupuesto.
	 * n es el número total de valores en el conjunto de datos.
	 */

	@Transient
	private Double deviationBudget() {
		Double avg = this.avgBudget();
		int numContracts = 0;
		double sumOfSquaredDifferences = 0.0;
		List<Contract> clientContracts = this.getClient().getContracts();

		for (Contract contract : clientContracts) {
			Money budget = contract.getBudget();
			if (budget != null && budget.getAmount() != null) {
				double difference = budget.getAmount() - avg;
				sumOfSquaredDifferences += difference * difference;
				numContracts++;
			}
		}

		Double deviation = 0.0;
		if (numContracts > 0)
			deviation = Math.sqrt(sumOfSquaredDifferences / numContracts);

		return deviation;
	}

	@Transient
	private Money minimumBudget() {
		Money minBudget = null;
		List<Contract> clientContracts = this.getClient().getContracts();

		for (Contract contract : clientContracts) {
			Money budget = contract.getBudget();
			if (budget != null && budget.getAmount() != null && (minBudget == null || budget.getAmount() < minBudget.getAmount()))
				minBudget = budget;
		}

		return minBudget;
	}

	@Transient
	private Money maximumBudget() {
		Money maxBudget = null;
		List<Contract> clientContracts = this.getClient().getContracts();

		for (Contract contract : clientContracts) {
			Money budget = contract.getBudget();
			if (budget != null && budget.getAmount() != null && (maxBudget == null || budget.getAmount() > maxBudget.getAmount()))
				maxBudget = budget;
		}

		return maxBudget;
	}


	// Relationships ----------------------------------------------------------
	@OneToOne
	private Client client;
}
