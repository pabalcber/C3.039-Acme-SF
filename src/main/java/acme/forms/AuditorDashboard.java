
package acme.forms;

import acme.client.data.AbstractForm;

public class AuditorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Double				totalCodeAuditsStatic;
	Double				totalCodeAuditsDynamic;
	Double				averageCodeAuditsPeriodLenght;
	Double				deviationCodeAuditsPeriodLenght;
	Double				minimumCodeAuditsPeriodLenght;
	Double				maximumCodeAuditsPeriodLenght;

}
