package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.*;

/*
   Created Владимир  at 17:31  07.05.2019
*/
public class HelperBase {
  protected WebDriver driver;
  private boolean acceptNextAlert = true;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }

  protected void click(By locator) {
    driver.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {     //если параметр задан НЕ null , то  очищать и записывать новое значение в поле
      String existingText = driver.findElement(locator).getAttribute("value");  // !только для полей ввода в экранной форме
      String existingText2 = driver.findElement(locator).getText();  // !не для  полей ввода!

      if (! text.equals(existingText)) {    // если новое значение отличается от имеющегося, то очищать и записывать новое значение
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
      }
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }


}
