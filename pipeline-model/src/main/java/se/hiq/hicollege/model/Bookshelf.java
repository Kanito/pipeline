package se.hiq.hicollege.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 *  A bookshelf made for books only
 * 
 * 	@author Robert Westin
 */
@Entity
@Table(name="BOOKSHELF")
@NamedQueries({
    @NamedQuery(name = Bookshelf.FIND_BY_BOOKSHELF, query = "select s from Bookshelf s where s.name = ?1")
})
public class Bookshelf {

	/**
	 * 	Generated ID
	 * 
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 	Name just for fun
	 * 
	 */
	private String name;
	

    public static final String FIND_BY_BOOKSHELF = "Bookshelf.FIND_BY_BOOKSHELF";
	
	/*
	 *  All this are given to you by jax-doclets 
	 * 
	 */
	@OneToMany(mappedBy="bookshelf", cascade=CascadeType.PERSIST)
	private List<Book> books = new ArrayList<Book>();
	
	
	/**
	 * Return tag is required oracle says {@link http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#requiredtags}
	 *  
	 * @return Returns the bookshelf id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Param tags are also required oracle says {@link http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#requiredtags}
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addBook(Book book){
		books.add(book);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasMoreThanFiveBooks(){
		return (books.size() > 5);
	}

}
