package se.hiq.hicollege.core.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import se.hiq.hicollege.model.Book;
import se.hiq.hicollege.model.Bookshelf;

@Stateless
public class BookServiceEjb implements BookService {

    @PersistenceContext(unitName = "pipeline")
    private EntityManager em;

	@Override
	public void createBook(Book book) {
		em.persist(book);
	}

	@Override
	public List<Book> getBooksForBookshelf(String bookShelfName) {
		
	    TypedQuery<Bookshelf> query = em.createNamedQuery(Bookshelf.FIND_BY_BOOKSHELF,
                Bookshelf.class);

        query.setParameter(1, bookShelfName);
        Bookshelf bookshelf =  query.getSingleResult();
    
		return bookshelf.getBooks();
	}

	@Override
	public void createBookInBookShelf(Book book, Bookshelf bookshelf) {
		bookshelf.addBook(book);
		em.persist(bookshelf);
	}

	@Override
	public List<Book> getAllBooks() {
		TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL_BOOKS, Book.class);
		List<Book> books = query.getResultList();
		return books;
	}
	
}