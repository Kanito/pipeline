package se.hiq.hicollege.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TopMenuExtendablePage {
	protected WebDriver driver;
	
	public TopMenuExtendablePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.ID, using="menu1")
	protected WebElement menuBokning;
	
	@FindBy(how = How.ID, using="menu2")
	public WebElement menuGrundbokning;
	
	@FindBy(how = How.ID, using="menu3")
	public WebElement menuGrundparametrar;
	
	protected WebElement submenu1;
	protected WebElement submenu2;
	protected WebElement submenu3;
	
	public void setText(WebElement webElement, String text){
		webElement.clear();
		webElement.sendKeys(text);
	}
	
}
