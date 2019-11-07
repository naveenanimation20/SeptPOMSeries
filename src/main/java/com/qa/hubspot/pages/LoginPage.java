package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;

	// 1. Page Objects / OR / By Locators
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");

	// 2. constructor of page class:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		elementUtil.waitForTitlePresent(Constants.Login_PAGE_TITLE, 15);

	}

	// 3. page actions / page methods:
	public String getPageTitle() {
		String title = elementUtil.waitForTitlePresent(Constants.Login_PAGE_TITLE, 15);
		System.out.println("login page title is: " + title);
		return title;
	}

	public boolean verifySignUpLink() {
		return elementUtil.isElementDisplayed(signUpLink);
	}

	public HomePage doLogin(String username, String pwd) {
		System.out.println("credentials are : " + username + " - " + pwd);
		elementUtil.doSendKeys(emailId, username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE, 15);

		return new HomePage(driver);
	}

}
