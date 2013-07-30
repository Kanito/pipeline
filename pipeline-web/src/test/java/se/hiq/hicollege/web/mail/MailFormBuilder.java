package se.hiq.hicollege.web.mail;

import se.hiq.hicollege.core.mail.MailForm;

public class MailFormBuilder {
	String emailSubject;
	String emailTo;
	String messageText;
	
	
	public static MailFormBuilder anMailForm() {
		return new MailFormBuilder();
	}

	public MailFormBuilder withEmailRecipient(String emailToAddress) {
		this.emailTo = emailToAddress;
		return this;
	}

	public MailFormBuilder withSubject(String subject) {
		this.emailSubject = subject;
		return this;
	}

	public MailFormBuilder withMessage(String message) {
		this.messageText = message;
		return this;
	}
	
	public MailForm build() {
		MailForm mailForm = new MailForm();
		mailForm.setEmailSubject(emailSubject);
		mailForm.setEmailTo(emailTo);
		mailForm.setMessageText(messageText);
		return mailForm;
	}
}
