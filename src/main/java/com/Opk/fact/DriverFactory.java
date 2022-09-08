package com.Opk.fact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author MaheshKumar
 *
 */

public class DriverFactory 
{
	public WebDriver driver;
	public Properties Propfile;
	public OptionsManager OptionsManager;
	
	public static ThreadLocal<WebDriver> threadlvDriver = new ThreadLocal<WebDriver>();
	
	public static Logger log = Logger.getLogger(DriverFactory.class);
	
	
	/**
	 * this method is used to initialize the driver on the basis of given
	 * browserName.
	 * 
	 * @param Propfile
	 * @return this returns driver
	 */
	
	public WebDriver initializeDriver(Properties Propfile)
	{
		String BrowserName = Propfile.getProperty("browser");
		
		System.out.println("Browser name is : " + BrowserName);
		log.info("Browser name is : " + BrowserName);
		
		OptionsManager = new OptionsManager(Propfile);
		
		if (BrowserName.equalsIgnoreCase("chrome")) 
		{
			log.info("Running test on chrome....");
			// driver = new ChromeDriver(optionsManager.optionsManager());

			if (Boolean.parseBoolean(Propfile.getProperty("remote"))) 
			{
				// remote execution on grid:
				initialize_RemoteWebDriver("chrome");
			} 
			else 
			{
				// local execution:
				log.info("Running tests on chrome -- local");
				WebDriverManager.chromedriver().setup();
				threadlvDriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
			}

		}
		
		else if (BrowserName.equalsIgnoreCase("firefox")) 
		{

			if (Boolean.parseBoolean(Propfile.getProperty("remote"))) 
			{
				// remote execution on grid:
				initialize_RemoteWebDriver("firefox");
			} 
			else 
			{
				// local execution:
				WebDriverManager.firefoxdriver().setup();
				// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				threadlvDriver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
			}
		}
		
		else if (BrowserName.equalsIgnoreCase("edge")) 
		{

			if (Boolean.parseBoolean(Propfile.getProperty("remote"))) 
			{
				// remote execution on grid:
				initialize_RemoteWebDriver("edge");
			} 
			else 
			{
				// local execution:
				WebDriverManager.edgedriver().setup();
				// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				threadlvDriver.set(new EdgeDriver(OptionsManager.getEdgeOptions()));
			}
		}
		
		else 
		{
			System.out.println("Please Enter the correct browser name : " + BrowserName);
			log.error("Please Enter the correct browser name :...." + BrowserName);
			//info, error, warn, fatal
		}
		getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getWebDriver().manage().deleteAllCookies();
		getWebDriver().manage().window().maximize();
		getWebDriver().get(Propfile.getProperty("url"));
		return getWebDriver();

	}
	
	
	private void initialize_RemoteWebDriver(String browserName) {

		System.out.println("===========Running tests on Selenium GRID - Remote Machine...." + browserName);

		if (browserName.equals("chrome")) {
			try {
				threadlvDriver.set(
						new RemoteWebDriver(new URL(Propfile.getProperty("huburl")), OptionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browserName.equals("firefox")) {
			try {
				threadlvDriver.set(
						new RemoteWebDriver(new URL(Propfile.getProperty("huburl")), OptionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browserName.equals("edge")) {
			try {
				threadlvDriver.set(new RemoteWebDriver(new URL(Propfile.getProperty("huburl")), OptionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static synchronized WebDriver getWebDriver()
	{
		return threadlvDriver.get();
		
	}
	
	/**
	 * this returns properties reference with all config properties
	 * 
	 * @return this returns properties reference
	 */
	
	
	public Properties initializePropfile() 
	{
		Propfile = new Properties();
		
		FileInputStream input = null;
		
		
		String envName = System.getProperty("env");
		System.out.println("Running test cases on environment: " + envName);
		
		log.info("Running test cases on env: "+ envName);
		
		if (envName == null) 
		{
			System.out.println("No env is given...hence running it on QA env by default....");
			try 
			{
				input = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}

		}
		
		else 
		{
			try 
			{
				switch (envName.toLowerCase()) 
				{
				case "qa":
					input = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					input = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					input = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					input = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "uat":
					input = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;

				default:
					System.out.println("Please pass the right environment.... " + envName);
					break;
				}
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}	
		}
		
		try 
		{
			Propfile.load(input);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return Propfile;
	}
		
	/**
	 * Taking screenshot
	 */
	public static String getScreenshot(String methodName) 
	{
		File srcFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
		
		String imgpath = System.getProperty("user.dir") + "/screenshot/" + methodName + ".png";
		File destination = new File(imgpath);
		try 
		{
			FileUtils.copyFile(srcFile, destination);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return imgpath;
	}
	
	

}
