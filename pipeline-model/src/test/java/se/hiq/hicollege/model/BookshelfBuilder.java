package se.hiq.hicollege.model;

import java.util.ArrayList;
import java.util.List;

import se.hiq.hicollege.model.Bookshelf;

class BookshelfBuilder{
	private List<Book> books = new ArrayList<Book>();
	private String name = "";
	
	public static BookshelfBuilder anBookshelf(){
		return new BookshelfBuilder();
	}
	
	public BookshelfBuilder withName(String name){
		this.name = name;
		return this;
	}
	
	public BookshelfBuilder withBook(Book book){
		books.add(book);
		return this;
	}
	
	public Bookshelf build(){
		Bookshelf bookshelf = new Bookshelf();
		for(Book book : books) {
			bookshelf.addBook(book);
		}
		bookshelf.setName(name);
		return bookshelf;
	}
}


