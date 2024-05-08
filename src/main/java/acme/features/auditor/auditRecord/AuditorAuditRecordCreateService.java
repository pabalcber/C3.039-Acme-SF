
package acme.features.auditor.auditRecord;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.codeAudits.CodeAudit;
import acme.features.auditor.codeAudits.AuditorCodeAuditRepository;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordCreateService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditRecordRepository	rp;

	@Autowired
	protected AuditorCodeAuditRepository	repository;


	@Override
	public void authorise() {
		int id;
		CodeAudit codeAudit;
		Auditor auditor;
		id = Integer.valueOf(super.getRequest().getData().values().stream().collect(Collectors.toList()).get(0).toString());
		codeAudit = this.repository.findCodeAuditById(id);
		auditor = this.repository.getAuditorbyCodeAuditId(id);
		boolean autorizacion = auditor.getUserAccount().getUsername().equals(super.getRequest().getPrincipal().getUsername());
		super.getResponse().setAuthorised(autorizacion);
	}
	@Override
	public void load() {
		AuditRecord object;
		Integer id;
		Auditor auditor;
		CodeAudit codeAudit;
		id = Integer.valueOf(super.getRequest().getData().values().stream().collect(Collectors.toList()).get(0).toString());
		object = new AuditRecord();
		codeAudit = this.repository.findCodeAuditById(id);
		auditor = this.repository.getAuditorbyCodeAuditId(id);
		object.setCodeAudit(codeAudit);
		object.setAuditor(auditor);

		super.getBuffer().addData(object);
	}
	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		super.bind(object, "codeAR", "startDate", "finishDate", "score", "link", "draftMode");
	}
	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		System.out.println(object.getEndPeriod());
		System.out.println(object.getStartPeriod());

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			AuditRecord existing;
			existing = this.rp.findOneAuditRecordByCode(object.getCode());
			super.state(existing == null, "code", "auditor.auditRecord.error.duplicated");
		}
		if (object.getStartPeriod() != null && object.getEndPeriod() != null) {
			if (!super.getBuffer().getErrors().hasErrors("getEndPeriod"))
				super.state(object.validatePeriod() == true, "finishDate", "auditor.auditRecord.error.period");

			if (!super.getBuffer().getErrors().hasErrors("getEndPeriod"))
				super.state(object.getEndPeriod().after(object.getStartPeriod()), "getEndPeriod", "auditor.auditRecord.error.period2");

			if (!super.getBuffer().getErrors().hasErrors("startDate"))
				super.state(object.getStartPeriod().before(object.getEndPeriod()), "getStartPeriod", "auditor.auditRecord.error.period3");
		}
		if (object.getStartPeriod() == null)
			if (!super.getBuffer().getErrors().hasErrors("getEndPeriod"))
				super.state(object.getStartPeriod() != null, "getEndPeriod", "auditor.auditRecord.error.periodN");
		if (object.getEndPeriod() == null)
			if (!super.getBuffer().getErrors().hasErrors("getStartPeriod"))
				super.state(object.getEndPeriod() != null, "getStartPeriod", "auditor.auditRecord.error.periodN");
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;

		this.repository.save(object);
	}
	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "startPeriod", "endPeriod", "mark", "link", "draftMode");

		dataset.put("codeAuditCode", object.getCodeAudit().getCode());

		super.getResponse().addData(dataset);
	}

}
