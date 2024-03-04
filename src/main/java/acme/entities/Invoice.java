
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	@NotBlank
	@Pattern(regexp = "IN-[0-9]{4}-[0-9]{4}", message = "Code must match pattern 'IN-[0-9]{4}-[0-9]{4}'")
	private String	code;

	@NotNull
	private Date	registrationTime;

	@NotNull
	private Date	dueDate;

	@Positive
	private int		quantity;

	@Positive
	private double	tax;

	private double	totalAmount;

	private String	furtherInformationLink;


	public Invoice() {
	}

	public Invoice(final String code, final Date registrationTime, final Date dueDate, final int quantity, final double tax, final String furtherInformationLink) {
		this.code = code;
		this.registrationTime = registrationTime;
		this.dueDate = dueDate;
		this.quantity = quantity;
		this.tax = tax;
		this.totalAmount = this.calculateTotalAmount(quantity, tax);
		this.furtherInformationLink = furtherInformationLink;
	}

	// Method to calculate total amount
	private double calculateTotalAmount(final int quantity, final double tax) {
		return quantity + tax;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + this.id + ", code=" + this.code + ", registrationTime=" + this.registrationTime + ", dueDate=" + this.dueDate + ", quantity=" + this.quantity + ", tax=" + this.tax + ", totalAmount=" + this.totalAmount
			+ ", furtherInformationLink=" + this.furtherInformationLink + "]";
	}
}
