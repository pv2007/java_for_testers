package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
   Created Владимир  at 16:59  06.05.2019
*/
public class NavigationHelper {
  private WebDriver driver;

  public NavigationHelper(WebDriver driver) {
    this.driver = driver;
  }

  public void gotoGroupPage(String groups) {
    driver.findElement(By.linkText(groups)).click();
  }
}
