/**
 * 
 */
package se.hiq.hicollege.restassured.apitests;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.hiq.hicollege.restassured.common.RuleSetUpRestAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;



public class BookSelectRESTIT{
	Logger log = LoggerFactory.getLogger(BookSelectRESTIT.class);
	
	@Rule
	public TestRule rule = new RuleSetUpRestAssured();
	
	@Test
	public void getAllBooks() throws Exception{
		getBooks();
	}

	/**
	 *  Reads from json-api that termcat is loaded.
	 */
	private void getBooks() throws Exception{
		
		String json = RestAssured.given().expect().
		contentType(ContentType.JSON).
		statusCode(200).
		body("book.isbn",hasItem("123456789")).
		body("book.title",hasItem("JavaScript: The Good Parts")).
		body("book.id",hasSize(greaterThan(1))).
		get("/books/getallbooks").asString();

		log.info(json);
		
	}

	
	
}

