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
import java.util.regex.MatchResult;

import static org.testng.Assert.fail;

/*
   Created Владимир  at 15:34  06.05.2019
*/
public class ApplicationManager {
  private final Properties properties;
  private WebDriver driver;   //доступен только внутри этого класса через вызов getDriver
  private String browser;
  private RegistrationHelper registrationHelper;

  public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    //вызов драйвера перенесли в getDriver()
  }


  public void stop() {
    if (driver != null) {
      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      driver.quit();
    }
  }

  // новые сессии можно открывать под несколько пользователей одновременно
  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public WebDriver getDriver() {
    if (driver == null){
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
    }
    return driver;
  }
}
