
package acme.entities.dashboards;

import java.util.List;

import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import acme.entities.projects.Project;
import acme.entities.userStories.Priority;
import acme.entities.userStories.UserStory;
import acme.roles.Manager;

public class ManagerDashboard extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------


	// Derived attributes -----------------------------------------------------
	@Transient
	public int mustUserStories() {
		int res = 0;
		List<Project> projects = this.manager.getProjects();
		for (Project p : projects)
			for (UserStory us : p.getUserStories())
				if (us.getPriority() == Priority.MUST)
					res++;
		return res;
	}

	@Transient
	public int shouldUserStories() {
		int res = 0;
		List<Project> projects = this.manager.getProjects();
		for (Project p : projects)
			for (UserStory us : p.getUserStories())
				if (us.getPriority() == Priority.SHOULD)
					res++;
		return res;
	}

	@Transient
	public int couldUserStories() {
		int res = 0;
		List<Project> projects = this.manager.getProjects();
		for (Project p : projects)
			for (UserStory us : p.getUserStories())
				if (us.getPriority() == Priority.COULD)
					res++;
		return res;
	}

	@Transient
	public int wontUserStories() {
		int res = 0;
		List<Project> projects = this.manager.getProjects();
		for (Project p : projects)
			for (UserStory us : p.getUserStories())
				if (us.getPriority() == Priority.WONT)
					res++;
		return res;
	}

	@Transient
	public Double averageEstimatedCost() {
		double total = 0.;
		double count = 0.;
		List<Project> projects = this.manager.getProjects();
		for (Project p : projects)
			for (UserStory us : p.getUserStories()) {
				total += us.getEstimatedCost();
				count += 1;
			}
		if (count == 0)
			return null;
		else
			return total / count;
	}

	@Transient
	public Double deviationEstimatedCost() {
		double desv = 0.;
		double count = 0.;
		List<Project> projects = this.manager.getProjects();
		Double avr = this.averageEstimatedCost();
		for (Project p : projects)
			for (UserStory us : p.getUserStories()) {
				desv += Math.abs(us.getEstimatedCost() - avr);
				count += 1;
			}
		if (count == 0)
			return null;
		else
			return desv / count;
	}

	@Transient
	public Double minimumEstimatedCost() {
		List<Project> projects = this.manager.getProjects();
		Double min = projects.get(0).getUserStories().get(0).getEstimatedCost();
		for (Project p : projects)
			for (UserStory us : p.getUserStories())
				if (us.getEstimatedCost() < min)
					min = us.getEstimatedCost();
		return min;
	}

	@Transient
	public Double maximumEstimatedCost() {
		List<Project> projects = this.manager.getProjects();
		Double max = 0.;
		for (Project p : projects)
			for (UserStory us : p.getUserStories())
				if (us.getEstimatedCost() > max)
					max = us.getEstimatedCost();
		return max;
	}

	@Transient
	public Money averageProjectCosts() {
		Money total = new Money();
		List<Project> projects = this.manager.getProjects();
		for (Project p : projects)
			total.setAmount(p.getCost().getAmount() + total.getAmount());
		total.setAmount(total.getAmount() / projects.size());
		return total;
	}

	@Transient
	public Money deviationProjectCosts() {
		Money desv = new Money();
		List<Project> projects = this.manager.getProjects();
		Money avr = this.averageProjectCosts();
		for (Project p : projects)
			desv.setAmount(desv.getAmount() + Math.abs(p.getCost().getAmount() - avr.getAmount()));
		desv.setAmount(desv.getAmount() / projects.size());
		return desv;
	}

	@Transient
	public Money minimumProjectCosts() {
		List<Project> projects = this.manager.getProjects();
		Money min = projects.get(0).getCost();
		for (Project p : projects)
			if (p.getCost().getAmount() < min.getAmount())
				min = p.getCost();
		return min;
	}

	@Transient
	public Money maximumProjectCosts() {
		List<Project> projects = this.manager.getProjects();
		Money max = projects.get(0).getCost();
		for (Project p : projects)
			if (p.getCost().getAmount() > max.getAmount())
				max = p.getCost();
		return max;
	}


	// Relationships ----------------------------------------------------------
	@Valid
	@OneToOne
	private Manager manager;
}
