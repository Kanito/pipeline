package se.hiq.hicollege.web.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

//http://ollivander.franzoni.eu/2011/08/30/mock-javamail-primer/

public class MailSenderFakeClient {

	private static Logger log = Logger.getLogger(MailSenderFakeClient.class);

	public List<String> processMail() {

		try {
			Session session = getMailSession();
			Store store = connect(session);
			Folder folder = openMailFolder(store);
			return findContent(folder);

		} catch (MessagingException e) {
			throw new RuntimeException(e);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public Session getMailSession() {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "smtp");
		props.setProperty("mail.imap.partialfetch", "0");
		log.debug("Getting session");
		return Session.getDefaultInstance(props, null);

	}

	public Store connect(Session session) throws MessagingException {
		log.debug("getting the session for accessing email.");
		Store store = session.getStore("imap");
		store.connect("mockserver.com", "testuser", "somepassword");
		log.debug("Connection established with IMAP server.");
		return store;

	}

	public Folder openMailFolder(Store store) throws MessagingException {
		Folder folder = store.getDefaultFolder();
		folder = folder.getFolder("inbox");
		folder.open(Folder.READ_ONLY);
		return folder;
	}

	public List<String> findContent(Folder folder) throws MessagingException,
			IOException {
		List<String> subjects = new ArrayList<String>();
		for (Message m : folder.getMessages()) {
			log.debug(m.getSubject());
			subjects.add(m.getSubject());
		}
		return subjects;
	}

}