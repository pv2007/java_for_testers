package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
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
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)){
      driver = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      String ieDriverFilePath = "C:\\Tools\\IEDriverServer.exe";
      //Specify the executable file path to sysem property.
      System.setProperty("webdriver.ie.driver", ieDriverFilePath);
      //Initiate web browser
      driver = new InternetExplorerDriver();
    }

    baseUrl = "http://127.0.0.1:81/addressbook/";
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    //driver.get("http://localhost:81/addressbook/");
    driver.get("http://127.0.0.1:81/addressbook/");
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
