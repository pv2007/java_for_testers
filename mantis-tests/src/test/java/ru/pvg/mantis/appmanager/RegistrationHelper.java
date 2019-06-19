package ru.pvg.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/*
   Created Владимир  at 13:03  18.06.2019
*/
public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) {
    super(app);  // вызов конструктора базового класса
  }

  public void start(String usernname, String email) {
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // time-out для ожидания загрузки страницы (
    driver.get(app.getProperty("web.baseUrl")+"/signup_page.php");
    type(By.name("username"), usernname);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Зарегистрироваться']"));


  }

  public void finish(String user, String confirmationLink, String password) {
    driver.get(confirmationLink);
    type(By.name("realname"), user);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }
}
