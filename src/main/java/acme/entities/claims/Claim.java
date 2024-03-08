
package acme.entities.claims;

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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.accounts.Administrator;
import acme.client.data.accounts.Anonymous;
import acme.entities.projects.Project;
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
public class Claim extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "^C-[0-9]{4}$")
	private String				code;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				instantiationMoment;

	@NotBlank
	@Length(max = 76)
	private String				heading;

	@NotBlank
	@Length(max = 101)
	private String				description;

	@NotBlank
	@Length(max = 101)
	private String				department;

	@Email
	private String				emailAddress;

	@URL
	@Length(max = 255)
	private String				link;

	// Relationships ----------------------------------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project				project;

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
	private Administrator		admin;

	@Valid
	@ManyToOne(optional = true)
	private Manager				manager;

	@Valid
	@ManyToOne(optional = true)
	private Anonymous			anonymus;

}
