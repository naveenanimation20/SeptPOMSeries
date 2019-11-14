package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.OperatingSystem;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	//public WebDriver driver;
	public Properties prop;

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	/**
	 * this method is used to initialize the driver on the basis of browsername
	 * 
	 * @param browserName
	 * @return driver
	 */
	public WebDriver init_driver(String browserName) {

		if (browserName.equals("chrome")) {
			//WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			///Users/NaveenKhunteta/Documents/workspace/SeptBatchPOMSeries/src/test/resources/drivers/chromedriver

			//System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
			WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
			tldriver.set(new ChromeDriver());
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
		} else if (browserName.equals("safari")) {

		} else {
			System.out.println(browserName + " Browser value is wrong, please pass the correct browser name....");
		}

		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();

		return getDriver();
	}

	/**
	 * this method is used to read the properties from config.properties file
	 * 
	 * @return prop
	 */
	public Properties init_properties() {
		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("config file is missing, please check it...");
		} catch (IOException e) {
			System.out.println("properties could not be loaded...");
			e.printStackTrace();
		}

		return prop;

	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
