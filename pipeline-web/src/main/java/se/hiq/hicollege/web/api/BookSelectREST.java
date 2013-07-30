package se.hiq.hicollege.web.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import se.hiq.hicollege.core.services.BookService;
import se.hiq.hicollege.model.Book;


@Stateless
@LocalBean
@Path("/books")
public class BookSelectREST {
	
	@EJB
	private BookService bookService;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("getallbooks")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
}
