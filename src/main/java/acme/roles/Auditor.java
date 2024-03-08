
package acme.roles;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractRole;

public class Auditor extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Max(76)
	private String				firm;

	@NotBlank
	@Max(26)
	private String				professionalID;

	@NotBlank
	@Max(101)
	private List<String>		certifications;

	@URL
	private String				link;
}
