package com.Opk.fact;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;



public class OptionsManager 
{
	private Properties property;
	private ChromeOptions chromop;
	private FirefoxOptions firefoxop;
	private EdgeOptions edgeop;
	
	public static Logger log = Logger.getLogger(OptionsManager.class);
	
	public OptionsManager(Properties property) 
	{
		this.property = property;
	}
	
	public ChromeOptions getChromeOptions() {
		log.info("adding chrome options....");
		chromop = new ChromeOptions();
		chromop.setAcceptInsecureCerts(true);
		if (Boolean.parseBoolean(property.getProperty("headless")))
			chromop.addArguments("--headless");
		if (Boolean.parseBoolean(property.getProperty("inchromopgnito")))
			chromop.addArguments("--inchromopgnito");

		if (Boolean.parseBoolean(property.getProperty("remote"))) {
			 chromop.setPlatformName("linux");
			 chromop.setCapability("enableVNC", true);
			 //chromop.setBrowserVersion(prop.getProperty("browserversion"));
//				chromop.setCapability("se:timeZone", "US/Pacific");
//				chromop.setCapability("se:screenResolution", "1920x1080");

//			Map<String, Object> selenoidOptions = new HashMap<>();
//			selenoidOptions.put("screenResolution", "1280x1024x24");
//			selenoidOptions.put("enableVNC", true);
//			selenoidOptions.put("name", prop.getProperty("testname"));
//			chromop.setCapability("selenoid:options", selenoidOptions);
		}

		return chromop;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		firefoxop = new FirefoxOptions();
		if (Boolean.parseBoolean(property.getProperty("headless")))
			firefoxop.addArguments("--headless");
		if (Boolean.parseBoolean(property.getProperty("incognito")))
			firefoxop.addArguments("--incognito");

		if (Boolean.parseBoolean(property.getProperty("remote"))) {
			firefoxop.setPlatformName("linux");
			firefoxop.setCapability("enableVNC", true);
			//firefoxop.setBrowserVersion(property.getProperty("browserversion"));
//			firefoxop.setCapability("se:timeZone", "US/Pacific");
//			firefoxop.setCapability("se:screenResolution", "1920x1080");
			
//			Map<String, Object> selenoidOptions = new HashMap<>();
//			selenoidOptions.put("screenResolution", "1280x1024x24");
//			selenoidOptions.put("enableVNC", true);
//			selenoidOptions.put("name", property.getProperty("testname"));
//			firefoxop.setCapability("selenoid:options", selenoidOptions);
		}
		return firefoxop;
	}
	public EdgeOptions getEdgeOptions() {
		edgeop = new EdgeOptions();
		if (Boolean.parseBoolean(property.getProperty("headless")))
			edgeop.addArguments("--headless");
		if (Boolean.parseBoolean(property.getProperty("incognito")))
			edgeop.addArguments("--incognito");

		if (Boolean.parseBoolean(property.getProperty("remote"))) {
			edgeop.setPlatformName("linux");
			edgeop.setCapability("enableVNC", true);
			edgeop.setBrowserVersion(property.getProperty("browserversion"));
			edgeop.setCapability("se:timeZone", "US/Pacific");
			edgeop.setCapability("se:screenResolution", "1920x1080");

		}
		return edgeop;
	}

}
