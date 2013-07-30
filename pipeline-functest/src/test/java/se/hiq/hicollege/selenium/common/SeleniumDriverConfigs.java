package se.hiq.hicollege.selenium.common;

import java.net.MalformedURLException;
import java.security.InvalidParameterException;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Configruation of desired capabilities in browsers.
 * @author BU00006
 *
 */
 class SeleniumDriverConfigs {
	public DesiredCapabilities desiredCapability;

	/**
	 * @param text system argument string to select browser
	 * @throws MalformedURLException
	 */
	SeleniumDriverConfigs(String text) throws InvalidParameterException{
		DriverToUse driverToUse = DriverToUse.driverFromString(text);
		switch (driverToUse) {
		case CHROME:
			desiredCapability = DesiredCapabilities.chrome();
			break;
		case FIREFOX:
			desiredCapability = DesiredCapabilities.firefox();
			
			break;
		case IE:
			desiredCapability = DesiredCapabilities.internetExplorer();
			//desiredCapability.setCapability("nativeEvents", false);
			desiredCapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			desiredCapability.setCapability("ignoreZoomSetting", true);
			break;
		default:
			throw new InvalidParameterException("Not in enum DriverToUse");
		}
	}
	
	public DesiredCapabilities getDesiredCapabilities(){
		return desiredCapability;
	}

	/**
	 * Enum to map system argument text to enum, to be used in switch/case
	 * @author BU00006
	 *
	 */
	public enum DriverToUse {
		FIREFOX("firefox"), CHROME("chrome"), IE("ie");

		private String text;

		private DriverToUse(String text) {
			this.text = text;
		}

		public static DriverToUse driverFromString(String text) {
			if (text != null) {
				for (DriverToUse d : DriverToUse.values()) {
					if (text.equalsIgnoreCase(d.text))
						return d;
				}
			}
			return null;
		}

	};
}
