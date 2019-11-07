package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class HomePageTest {

	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browser = prop.getProperty("browser");
		driver = basePage.init_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		System.out.println("home page title is: " + title);
		Assert.assertEquals(title, "Reports dashboard", "home page title is missing....");
	}

	@Test(priority = 2)
	public void verifyHomePageHeaderTest() {
		Assert.assertTrue(homePage.isHeaderPresent(), "home page header is not present...");
		String header = homePage.getHomePageHeaderValue();
		System.out.println("home page header is: " + header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void verifyLoggedInUserTest() {
		Assert.assertTrue(homePage.isAccountNamePresent());
		String accountName = homePage.getAccountNameValue();
		System.out.println("logged in account user name:" + accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
