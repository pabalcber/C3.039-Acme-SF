
package acme.features.auditor.auditRecord;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.codeAudits.CodeAudit;

public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id = :id")
	AuditRecord findAuditRecordById(int id);

	@Query("select a from AuditRecord a")
	Collection<AuditRecord> findAllAuditRecords();

	@Query("select a.mark from AuditRecord a where a.codeAudit = :ca")
	List<String> getMarkOfAsociatedAuditRecords(CodeAudit ca);

	@Query("select a from AuditRecord a where a.code = :code")
	AuditRecord findOneAuditRecordByCode(String code);
}
