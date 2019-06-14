package ru.pvg.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

/*
   Created Владимир  at 15:34  06.05.2019
*/
public class ApplicationManager {
  private final Properties properties;
  WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private String browser;

  public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      driver = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      String ieDriverFilePath = "C:\\Tools\\IEDriverServer.exe";
      //Specify the executable file path to sysem property.
      System.setProperty("webdriver.ie.driver", ieDriverFilePath);
      //Initiate web browser
      driver = new InternetExplorerDriver();
    }

    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // time-out для ожидания загрузки страницы (
    // (ожидание появления элемента на странице), можно ставить 0 для быстрых сайтов
    driver.get(properties.getProperty("web.baseUrl"));
  }


  public void stop() {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
