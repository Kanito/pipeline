package se.hiq.hicollege.core.mail;

import javax.mail.MessagingException;

public interface EmailService {

	public abstract void sendEmail(MailForm mailForm) throws MessagingException;

}