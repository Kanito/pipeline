package se.hiq.hicollege.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Table(name="BOOK")
@Entity
@NamedQueries({
    @NamedQuery(name = Book.FIND_ALL_BOOKS, query = "select s from Book s ")
})
public class Book {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

    public static final String FIND_ALL_BOOKS = "Book.FIND_ALL_BOOKS";

	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	@XmlTransient
	public Bookshelf getBookshelf() {
		return bookshelf;
	}

	public void setBookshelf(Bookshelf bookshelf) {
		this.bookshelf = bookshelf;
	}

	@Column(unique=true)
	private Long isbn;
	private String title;
	
	@ManyToOne
	private Bookshelf bookshelf;
	
	public Book(){};
	
	public Book(String title, Long isbn){
		this.setTitle(title);
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString(){
		return "Book [id =" + id + ", title=" + title + ", isbn=" + isbn + ", bookshelf=" + bookshelf.getName() + "]";
	}
	
}
	