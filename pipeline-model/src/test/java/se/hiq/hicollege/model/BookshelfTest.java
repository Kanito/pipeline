package se.hiq.hicollege.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import se.hiq.hicollege.model.Bookshelf;
import static se.hiq.hicollege.model.BookshelfBuilder.anBookshelf;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BookshelfTest {
	
	@Mock
	private Book book;
	private Bookshelf bookshelf;
	
	@Before 
	public void setUp(){
		bookshelf = new Bookshelf();
	}
	
	@Test
	public void noBooksInListShouldReturnFalse(){
		assertThat(bookshelf.hasMoreThanFiveBooks(),is(false));
	}
	
	@Test
	public void fiveBooksPlusOneShouldReturnTrue(){
		Bookshelf bookshelf = anBookshelf().withName("myFirstBookShelf").withBook(new Book())
		.withBook(new Book())
		.withBook(new Book())
		.withBook(new Book())
		.withBook(new Book())
		.withBook(new Book()).build();
		assertThat(bookshelf.hasMoreThanFiveBooks(), is(true));
	}
	
	@Test
	public void bookshelfHasName(){
		Bookshelf bookshelf = anBookshelf().withName("myFirstBookShelf").withBook(new Book()).build();
		assertThat("Name of bookshelf", bookshelf.getName(), containsString("myFirst"));
		
		//create mock bookshelf bs
		Bookshelf bs = mock(Bookshelf.class);
		when(bs.getName()).thenReturn("myFirstMockBook");
		assertThat("Name of bookshelf", bs.getName(), containsString("MockBook"));
	}
}
