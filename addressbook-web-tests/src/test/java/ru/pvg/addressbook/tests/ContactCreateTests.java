package ru.pvg.addressbook.tests;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import static org.openqa.selenium.By.linkText;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


/*
   Created Владимир  at 11:26  08.05.2019
*/
public class ContactCreateTests extends ConTestBase {
  private boolean acceptNextAlert = true;


  @Test
    public void testContactCreation() throws Exception {

    driver.get("http://localhost:81/addressbook/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();



      driver.findElement(linkText("add new")).click();
      driver.findElement(By.name("firstname")).click();
      driver.findElement(By.name("firstname")).clear();
      driver.findElement(By.name("firstname")).sendKeys("z1");
      driver.findElement(By.name("middlename")).clear();
      driver.findElement(By.name("middlename")).sendKeys("z2");
      driver.findElement(By.name("lastname")).clear();
      driver.findElement(By.name("lastname")).sendKeys("z3");
      driver.findElement(By.name("nickname")).clear();
      driver.findElement(By.name("nickname")).sendKeys("z4");
      driver.findElement(By.name("title")).clear();
      driver.findElement(By.name("title")).sendKeys("z5");
      driver.findElement(By.name("company")).clear();
      driver.findElement(By.name("company")).sendKeys("6");
      driver.findElement(By.name("address")).clear();
      driver.findElement(By.name("address")).sendKeys("7");
      driver.findElement(By.name("home")).clear();
      driver.findElement(By.name("home")).sendKeys("z8");
      driver.findElement(By.name("mobile")).clear();
      driver.findElement(By.name("mobile")).sendKeys("z9");
      driver.findElement(By.name("work")).clear();
      driver.findElement(By.name("work")).sendKeys("z10");
      driver.findElement(By.name("fax")).clear();
      driver.findElement(By.name("fax")).sendKeys("z11");
      driver.findElement(By.name("submit")).click();
      driver.findElement(linkText("home")).click();
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

