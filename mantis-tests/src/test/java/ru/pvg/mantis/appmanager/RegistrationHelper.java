package ru.pvg.mantis.appmanager;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/*
   Created Владимир  at 13:03  18.06.2019
*/
public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver driver;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    driver = app.getDriver();
  }

  public void start(String usernname, String emails) {
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // time-out для ожидания загрузки страницы (
    driver.get(app.getProperty("web.baseUrl")+"/singup_page.php");
  }
}
