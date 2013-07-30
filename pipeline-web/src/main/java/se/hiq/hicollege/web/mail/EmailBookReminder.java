package se.hiq.hicollege.web.mail;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import se.hiq.hicollege.core.mail.EmailService;
import se.hiq.hicollege.core.mail.MailForm;
import se.hiq.hicollege.core.services.BookService;
import se.hiq.hicollege.model.Book;

public class EmailBookReminder extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8197373596258212646L;

	@EJB
	private BookService bookService;
	
	@EJB 
	private EmailService emailBean;
	

	Logger logger = Logger.getLogger(EmailBookReminder.class);
	
	public void doPost( HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
		
		List<Book> booklist = bookService.getAllBooks();
		String title = request.getParameter("title");
		String emailTo = request.getParameter("emailTo");
		String subject = request.getParameter("subject");
		
		boolean booksFound = false;
		String result = "You searched for \"" + title + "\".<br><br>";		
		for (int i=0; i<booklist.size(); i++) {
			if (booklist.get(i).getTitle().toLowerCase().contains(title.toLowerCase())) {
				result += booklist.get(i).getTitle() + ", " + booklist.get(i).getIsbn() + "<br>";
				booksFound = true;
			}
		}
		
		if (!booksFound) {
			result = "Your search \"" + title + "\" did not match any books.";
		}
		
		try {
			MailForm mailForm = new MailForm();
			mailForm.setEmailSubject(subject);
			mailForm.setEmailTo(emailTo);
			mailForm.setMessageText(result);
			emailBean.sendEmail(mailForm);
		} catch (MessagingException e) {
			logger.error(e);
		}
		
		request.setAttribute("styles", result);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
				
	}
	
}
