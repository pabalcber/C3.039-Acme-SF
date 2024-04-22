
package acme.features.authenticated.notice;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.notices.Notice;

@Repository
public interface AuthenticatedNoticeRepository extends AbstractRepository {

	@Query("select n from Notice n where n.instantiationMoment > :deadline")
	Collection<Notice> findRecentNotices(Date deadline);

	@Query("select n from Notice n where n.id = :id")
	Notice findOneNoticeById(int id);

}
