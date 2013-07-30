package se.hiq.hicollege.core.services;

import java.util.List;

import se.hiq.hicollege.model.Book;
import se.hiq.hicollege.model.Bookshelf;

/**
 * Provides access to books.
 */
public interface BookService {

    void createBook(Book book);
    
    void createBookInBookShelf(Book book, Bookshelf bookshelf);

	List<Book> getBooksForBookshelf(String bookshelfName);

	List<Book> getAllBooks();
	
}
