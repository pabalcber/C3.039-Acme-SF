
package acme.entities.notices;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.accounts.Administrator;
import acme.roles.Auditor;
import acme.roles.Consumer;
import acme.roles.Manager;
import acme.roles.Provider;
import acme.roles.clients.Client;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notice extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				instantiationMoment;

	@NotBlank
	@Length(min = 1, max = 76)
	private String				title;

	@NotBlank
	@Length(min = 1, max = 76)
	private String				author;

	@NotBlank
	@Length(min = 1, max = 101)
	private String				message;

	@Email
	private String				email;

	@URL
	@Length(max = 255)
	private String				link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Valid
	@ManyToOne(optional = true)
	private Consumer			consumer;

	@Valid
	@ManyToOne(optional = true)
	private Provider			provider;

	@Valid
	@ManyToOne(optional = true)
	private Client				client;

	@Valid
	@ManyToOne(optional = true)
	private Auditor				auditor;

	@Valid
	@ManyToOne(optional = true)
	private Administrator		administrator;

	@Valid
	@ManyToOne(optional = true)
	private Manager				manager;

}
