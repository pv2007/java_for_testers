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

  public void gotoPage(String groups) {
    //добавим проверку, на какой странице находимся,  перед выполнением перехода для каждого варианта
    if (groups.equals("groups")) {
      if (isElementPresent(By.tagName("h1"))
              && driver.findElement(By.tagName("h1")).getText().equals("Groups") // проверка что на странице есть заголовок h1 с текстом Groups
              && isElementPresent(By.name("new"))) {                              // и кнопкой new
        return;
      } else {
        click(By.linkText(groups));
      }
    } else {
      if ((groups.equals("home"))) {
          // if (isElementPresent(By.name("searchstring"))) {   //поиск по наличию строки поиска "searchstring"
        if (isElementPresent(By.id("maintable"))) {             //поиск по наличию таблицы с id="maintable"
          return;
        } else {
          click(By.linkText(groups));
        }
      } else {
        click(By.linkText(groups));
      }
    }
  }

}
