package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest {

	// BM - T - AM

	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browser = prop.getProperty("browser");
		driver = basePage.init_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}

	@Description("verify login page title test..")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyLoginPagTitleTest() {
		Assert.assertEquals(loginPage.getPageTitle(), "hh", "Login page title is mismatched...");
	}

	@Description("verify sign up link test..")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink(), "sign up link is not displayed....");
	}

	@Description("verify login page title test..")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
