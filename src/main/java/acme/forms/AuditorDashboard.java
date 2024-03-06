
package acme.forms;

import acme.client.data.AbstractForm;

public class AuditorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer				staticCodeAudits;

	Integer				dynamicCodeAudits;

	Double				averageNumAuditRecords;

	Double				deviationNumAuditRecords;

	Integer				minNumAuditRecords;

	Integer				maxNumAuditRecords;

	Double				averageTimeAuditRecords;

	Double				deviationTimeAuditRecords;

	Integer				minTimeAuditRecords;

	Integer				maxTimeAuditRecords;

}
