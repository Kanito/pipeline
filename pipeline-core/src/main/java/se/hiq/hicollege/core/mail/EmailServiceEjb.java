package se.hiq.hicollege.core.mail;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import org.apache.log4j.Logger;

@Stateless
@Remote
public class EmailServiceEjb implements EmailService {

	Logger logger = Logger.getLogger(EmailServiceEjb.class);

	@Resource(name = "mail/mailSession")
	private Session mailSession;

	/* (non-Javadoc)
	 * @see se.hiq.hicollege.core.mail.EmailService#sendEmail(se.hiq.hicollege.core.mail.MailForm)
	 */
	@Override
	public void sendEmail(MailForm mailForm) throws MessagingException {
		logger.debug("Started sendEmail");
		Message message = mailForm.getMessage(mailSession);
		Date timeStamp = new Date();
		message.setSentDate(timeStamp);
		Transport.send(message);
		logger.debug("Message sent at: " + timeStamp);
	}

}
