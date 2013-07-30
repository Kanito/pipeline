/**
 * 
 */
package se.hiq.hicollege.selenium.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author bu00006
 *
 */
public class RuleSetUpDriver extends TestWatcher {
	private final Logger logger = Logger.getLogger(RuleSetUpDriver.class);
	BaseConnection baseConnection;
	String address;
	public final static long WAITSECONDS=10;
	public final static long WAIT_ONE_SECOND = 1;
	public final static long SHORT_WAIT_SECONDS = 2;
	
	/**
	 * 
	 */
	public RuleSetUpDriver(BaseConnection baseConnection, String address) {
		this.baseConnection = baseConnection;
		this.address = address;
	}
	
	@Override
	protected void starting(Description description) {
		try {
			setUpDriver();
		} catch (MalformedURLException e) {
			logger.error(e);
		}
		baseConnection.driver.get(address);
		super.starting(description);
	}
	
	@Override
	protected void finished(Description description) {
		baseConnection.driver.quit();	
		super.finished(description);
	}
	
	public void setUpDriver() throws MalformedURLException{
		baseConnection.driver = new RemoteWebDriver(new URL(BaseConnection.SELENIUM_HUB_URL),BaseConnection.seleniumConfig.getDesiredCapabilities());
		baseConnection.driver.manage().timeouts().implicitlyWait(WAITSECONDS, TimeUnit.SECONDS);
		baseConnection.driver.manage().deleteAllCookies();
		baseConnection.waitSiteLoad = new WebDriverWait(baseConnection.driver, WAITSECONDS);
	}
	/*
	public void fixIECertificateWindow(){
		if (BaseConnection.seleniumConfig.getDesiredCapabilities().getBrowserName().equals("internet explorer")) {
			baseConnection.driver.navigate()
					.to("javascript:document.getElementById('overridelink').click()");
		}
	}*/
}
