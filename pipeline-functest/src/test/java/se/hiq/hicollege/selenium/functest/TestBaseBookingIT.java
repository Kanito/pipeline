package se.hiq.hicollege.selenium.functest;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import se.hiq.hicollege.selenium.common.BaseConnection;
import se.hiq.hicollege.selenium.common.RuleSetUpDriver;
import se.hiq.hicollege.selenium.common.RuleWatchmanScreenshot;
import se.hiq.hicollege.selenium.pageobjects.BasicBookingPage;



/**
 * Autotesting of Base booking
 *
 * @author BU00006
 * 
 * 
 */
@Ignore
public class TestBaseBookingIT extends BaseConnection {
	String STARTPAGE = "pipeline";
	
	BasicBookingPage bbp;
	
	@Rule
	public TestRule chain = RuleChain.outerRule(
			new RuleSetUpDriver(this, TARGET_SERVER_URL + STARTPAGE))
			.around(new RuleWatchmanScreenshot(this, SCREENSHOT_FILEPATH));
	
	public TestBaseBookingIT() throws Exception {
		super();
	}
	
	@Test
	public void searchBook() throws InterruptedException{	
        WebElement element = driver.findElement(By.id("titleId"));
        element.sendKeys("Swing");
        element.submit();
        //check that we've got the expected isbn
        assertTrue(driver.getPageSource().contains("91011121314"));
	}
}
