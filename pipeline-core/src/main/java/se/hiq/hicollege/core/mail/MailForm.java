package se.hiq.hicollege.core.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailForm {
	String emailSubject;
	String emailTo;
	String messageText;

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Message getMessage(Session mailSession) throws MessagingException {
		Message message = new MimeMessage(mailSession);
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo, false));
		message.setSubject(emailSubject);
		message.setText(messageText);
		return message;
	}
}

