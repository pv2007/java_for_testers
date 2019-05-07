package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
   Created Владимир  at 16:59  06.05.2019
*/
public class NavigationHelper extends HelperBase{


  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void gotoGroupPage(String groups) {
    click(By.linkText(groups));
  }
}
