/**
 * 
 */
package se.hiq.hicollege.selenium.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;


/**
 * @author Robert Westin <robert.westin@hiq.se>
 * 
 */
public class RuleWatchmanScreenshot extends TestWatcher{
		String SCREENSHOT_FILEPATH;
		BaseConnection testClass;
			
		/**
		 * Rule for JUnit to take Selenium WebDriver screenshot, quits WebDriver when finished.  
		 * 
		 * @param testClass	Test class that holds WebDriver extended from BaseConnection
		 * @param SCREENSHOT_FILEPATH Path were to save screenshots
		 */
		public RuleWatchmanScreenshot(BaseConnection testClass, String SCREENSHOT_FILEPATH) {
			this.SCREENSHOT_FILEPATH = SCREENSHOT_FILEPATH;
			this.testClass = testClass;
		}
	
		/** 
		 * Take screenshot when test failed.
		 * @see org.junit.rules.TestWatcher#failed(java.lang.Throwable, org.junit.runner.Description)
		 */
		@Override
		protected void failed(Throwable e, Description description) {
			
			try {
				takeScreenShot(description.getMethodName());
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
			
			super.failed(e, description);
			
		}

		/**
		 * 	Quit WebDriver.
		 * 
		 * @see org.junit.rules.TestWatcher#finished(org.junit.runner.Description)
		 */
		@Override
		protected void finished(Description description) {
		super.finished(description);
		}
		
		/**
		 * Take screenshot and save on disk
		 * 
		 * @param fileName
		 * @throws IOException
		 */
		private void takeScreenShot(String fileName) throws IOException {
			WebDriver augmentedDriver = new Augmenter().augment(testClass.driver);
			File screenShot = ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShot, new File(SCREENSHOT_FILEPATH+fileName+ ".png"));
		}
	
}
