package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;

	By header = By.className("private-page__title");
	By accountName = By.cssSelector("a#account-menu>span");

	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	public String getHomePageTitle() {
		return elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE, 15);
	}

	public boolean isHeaderPresent() {
		return elementUtil.isElementDisplayed(header);
	}

	public String getHomePageHeaderValue() {
		return elementUtil.doGetText(header);
	}

	public boolean isAccountNamePresent() {
		return elementUtil.isElementDisplayed(accountName);
	}

	public String getAccountNameValue() {
		return elementUtil.doGetText(accountName);
	}

	public void clickOnContacts() {
		elementUtil.waitForElementPresent(mainContactsLink, 15);
		elementUtil.doClick(mainContactsLink);
		
		elementUtil.waitForElementPresent(childContactsLink, 5);
		elementUtil.doClick(childContactsLink);
	}

	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}

}
