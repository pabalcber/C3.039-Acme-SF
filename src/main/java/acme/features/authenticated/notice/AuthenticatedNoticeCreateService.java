
package acme.features.authenticated.notice;

import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.notices.Notice;

@Service
public class AuthenticatedNoticeCreateService extends AbstractService<Authenticated, Notice> {

}
