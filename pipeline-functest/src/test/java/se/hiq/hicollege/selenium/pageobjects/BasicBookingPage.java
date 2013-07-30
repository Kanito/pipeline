package se.hiq.hicollege.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasicBookingPage extends TopMenuExtendablePage{
	
	public static final String URL = "grundbokning";
	public static final String TITLE = "Grundbokning";
	
	
	public BasicBookingPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how=How.ID,using="searchinput")
	private WebElement inputSearchInput;
	
	@FindBy(how=How.ID,using="displaylimit10")
	private WebElement buttonDisplayLimit10;
	
	@FindBy(how=How.ID,using="displaylimit25")
	private WebElement buttonDisplayLimit25;
	
	@FindBy(how=How.ID,using="displaylimit50")
	private WebElement buttonDisplayLimit50;
	
	@FindBy(how=How.ID,using="example_info")
	private WebElement searchInfoText;
	
	private WebElement tabledata;

	
	//Element with text "Inga poster i databasen"
	public boolean noMatchingPosts(){
		return tabledata.findElement(By.className("dataTables_empty")).isDisplayed();
	}
	
	public void searchFor(String searchText){
		
		inputSearchInput.clear();
		inputSearchInput.sendKeys(searchText);
	}
		

}
