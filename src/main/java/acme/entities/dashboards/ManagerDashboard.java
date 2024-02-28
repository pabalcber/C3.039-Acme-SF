
package acme.entities.dashboards;

import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import acme.entities.projects.Project;
import acme.entities.userStories.Priority;
import acme.entities.userStories.UserStory;

public class ManagerDashboard extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------


	// Derived attributes -----------------------------------------------------
	@Transient
	public int mustUserStories() {
		int res = 0;
		for (UserStory us : this.userStories)
			if (us.getPriority() == Priority.MUST)
				res++;
		return res;
	}

	@Transient
	public int shouldUserStories() {
		int res = 0;
		for (UserStory us : this.userStories)
			if (us.getPriority() == Priority.SHOULD)
				res++;
		return res;
	}

	@Transient
	public int couldUserStories() {
		int res = 0;
		for (UserStory us : this.userStories)
			if (us.getPriority() == Priority.COULD)
				res++;
		return res;
	}

	@Transient
	public int wontUserStories() {
		int res = 0;
		for (UserStory us : this.userStories)
			if (us.getPriority() == Priority.WONT)
				res++;
		return res;
	}

	@Transient
	public Double averageEstimatedCost() {
		double total = 0.;
		for (UserStory us : this.userStories)
			total += us.getEstimatedCost();
		return total / this.userStories.size();
	}

	@Transient
	public Double deviationEstimatedCost() {
		double desv = 0.;
		Double avr = this.averageEstimatedCost();
		for (UserStory us : this.userStories)
			desv += Math.abs(us.getEstimatedCost() - avr);
		return desv / this.userStories.size();
	}

	@Transient
	public Double minimumEstimatedCost() {
		Double min = this.userStories.get(0).getEstimatedCost();
		for (UserStory us : this.userStories)
			if (us.getEstimatedCost() < min)
				min = us.getEstimatedCost();
		return min;
	}

	@Transient
	public Double maximumEstimatedCost() {
		Double max = this.userStories.get(0).getEstimatedCost();
		for (UserStory us : this.userStories)
			if (us.getEstimatedCost() > max)
				max = us.getEstimatedCost();
		return max;
	}

	@Transient
	public Money averageProjectCosts() {
		Money total = new Money();
		for (Project p : this.projects)
			total.setAmount(p.getCost().getAmount() + total.getAmount());
		total.setAmount(total.getAmount() / this.projects.size());
		return total;
	}

	@Transient
	public Money deviationProjectCosts() {
		Money desv = new Money();
		Money avr = this.averageProjectCosts();
		for (Project p : this.projects)
			desv.setAmount(desv.getAmount() + Math.abs(p.getCost().getAmount() - avr.getAmount()));
		desv.setAmount(desv.getAmount() / this.projects.size());
		return desv;
	}

	@Transient
	public Money minimumProjectCosts() {
		Money min = this.projects.get(0).getCost();
		for (Project p : this.projects)
			if (p.getCost().getAmount() < min.getAmount())
				min = p.getCost();
		return min;
	}

	@Transient
	public Money maximumProjectCosts() {
		Money max = this.projects.get(0).getCost();
		for (Project p : this.projects)
			if (p.getCost().getAmount() > max.getAmount())
				max = p.getCost();
		return max;
	}


	// Relationships ----------------------------------------------------------
	@Valid
	@OneToMany
	private List<UserStory>	userStories;
	@Valid
	@OneToMany
	private List<Project>	projects;
}
