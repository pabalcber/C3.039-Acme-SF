
package acme.entities.dashboards;

import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import acme.client.data.AbstractEntity;
import acme.entities.projects.Project;

public class ManagerDashboard extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	private int					mustUserStories;
	private int					shouldUserStories;
	private int					couldUserStories;
	private int					wontUserStories;


	// Derived attributes -----------------------------------------------------
	@Transient
	public Double averageEstimatedCosts() {
		double total = .0;
		for (Project p : this.projects)
			total = p.getCost() + total;
		return total / this.projects.size();
	}

	@Transient
	public Double deviationEstimatedCosts() {
		double desv = .0;
		for (Project p : this.projects)
			desv = desv + Math.abs(p.getCost() - this.averageEstimatedCosts());
		return desv / this.projects.size();
	}

	@Transient
	public Double minimumEstimatedCosts() {
		double min = this.projects.get(0).getCost();
		for (Project p : this.projects)
			if (p.getCost() < min)
				min = p.getCost();
		return min;
	}

	@Transient
	public Double maximumEstimatedCosts() {
		double max = this.projects.get(0).getCost();
		for (Project p : this.projects)
			if (p.getCost() > max)
				max = p.getCost();
		return max;
	}


	// Relationships ----------------------------------------------------------
	@Valid
	@OneToMany
	private List<Project> projects;
}
