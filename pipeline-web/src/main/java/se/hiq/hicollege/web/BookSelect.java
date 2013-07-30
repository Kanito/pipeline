package se.hiq.hicollege.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.hiq.hicollege.core.services.BookService;
import se.hiq.hicollege.model.Book;

public class BookSelect extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3252724675797519197L;
	@EJB
	private BookService bookService;
	
	
	public void doPost( HttpServletRequest request,
	                      HttpServletResponse response)
	                      throws IOException, ServletException {
		
		List<Book> booklist = bookService.getAllBooks();
		String title = request.getParameter("title");
		
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
		
		request.setAttribute("styles", result);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
	}
}