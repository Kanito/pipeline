package se.hiq.hicollege.web.mail;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static se.hiq.hicollege.web.mail.MailFormBuilder.anMailForm;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.mock_javamail.Mailbox;

//http://ollivander.franzoni.eu/2011/08/30/mock-javamail-primer/
public class IMAPMailTest {
	
	MailSenderFakeClient mailSenderFakeClient = new MailSenderFakeClient();
	final Session session = Session.getInstance(System.getProperties());
	
	@Before
	public void setUp() throws Exception {
		MimeMessage msg = new MimeMessage(session);
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("testuser@mockserver.com",false));
		msg.setSubject("Some Subject");
		msg.setText("sometext");
		Transport.send(msg);
	}
	
	@Test
	public void createMailFormAndSend() throws MessagingException{
		String subject = "Subject for test";
		
		Message message = anMailForm()
				.withSubject(subject).
		withEmailRecipient("testuser@mockserver.com").
		withMessage("hej hej").build().getMessage(session);
		
		Transport.send(message);
		assertThat("mailbox has subject", mailSenderFakeClient.processMail(),hasItem(subject));
	}
	
	@After
	public void tearDown() throws Exception {
		Mailbox.clearAll();
	}

}
