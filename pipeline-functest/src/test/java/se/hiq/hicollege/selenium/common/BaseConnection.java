package se.hiq.hicollege.selenium.common;

import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

@Ignore public class BaseConnection{

	public static final long MINIMAL_TIME = 100;
	public static final long SHORT_TIME = 500;
	public static final long NORMAL_TIME = 1000;
	public static final long MAXIMAL_TIME = 10000;
	
	protected String SCREENSHOT_FILEPATH;
	protected String SELENIUM_TARGET_BROWSER;
	public WebDriverWait waitSiteLoad;
	public WebDriver driver;
	public static SeleniumDriverConfigs seleniumConfig;
	public static String SELENIUM_HUB_URL;
	public static String TARGET_SERVER_URL;
	
	public BaseConnection(){
		
		
		//Default values
		SCREENSHOT_FILEPATH ="c:\\screenshots\\";
		SELENIUM_TARGET_BROWSER="firefox";
		SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";
		TARGET_SERVER_URL = "http://localhost:8181/";
		
		String sysHub = System.getProperty("test.selenium.hub.url");
		String sysTarget = System.getProperty("test.target.server.url");
		String sysScreenshotFilepath = System.getProperty("test.screenshot.filepath");
		String sysBrowser = System.getProperty("test.selenium.browser");
			
		if(sysHub!=null)SELENIUM_HUB_URL = sysHub;
		if(sysTarget!=null)TARGET_SERVER_URL = sysTarget;
		if(sysScreenshotFilepath!=null)SCREENSHOT_FILEPATH = sysScreenshotFilepath;		
		if(sysBrowser!=null)SELENIUM_TARGET_BROWSER=sysBrowser;
		
		

		
		seleniumConfig = new SeleniumDriverConfigs(SELENIUM_TARGET_BROWSER);		
	}
	
	public static void browserSpecificDelay(long time, DesiredCapabilities browser) throws InterruptedException{
		if(browser.equals(seleniumConfig.desiredCapability)){
			Thread.sleep(time);
		}
	}
	
    public static boolean excludeBrowser(DesiredCapabilities BrowserToExclude){
    	if(seleniumConfig.desiredCapability.equals(BrowserToExclude)){
    		return true;
    	}
    	return false;
    }
    
}
