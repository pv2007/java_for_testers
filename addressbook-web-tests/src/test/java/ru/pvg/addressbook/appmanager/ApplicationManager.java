package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

/*
   Created Владимир  at 15:34  06.05.2019
*/
public class ApplicationManager {
  WebDriver driver;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  public void init() {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:81/addressbook/";
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.get("http://localhost:81/addressbook/");
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    contactHelper = new ContactHelper(driver);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    navigationHelper.gotoGroupPage("Logout");
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }


  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
