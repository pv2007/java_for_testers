package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pvg.addressbook.model.ContactData;

import static org.openqa.selenium.By.linkText;

/*
   Created Владимир  at 16:14  08.05.2019
*/
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillContactForm(ContactData contactData) {
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    driver.findElement(By.name("middlename")).clear();
    driver.findElement(By.name("middlename")).sendKeys(contactData.getMiddleName());
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    driver.findElement(By.name("nickname")).clear();
    driver.findElement(By.name("nickname")).sendKeys(contactData.getNickName());
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys(contactData.getTitle());
    driver.findElement(By.name("company")).clear();
    driver.findElement(By.name("company")).sendKeys(contactData.getCompany());
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
    driver.findElement(By.name("home")).clear();
    driver.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
    driver.findElement(By.name("mobile")).clear();
    driver.findElement(By.name("mobile")).sendKeys(contactData.getMobilePhone());
    driver.findElement(By.name("work")).clear();
    driver.findElement(By.name("work")).sendKeys(contactData.getWorkPhone());
    driver.findElement(By.name("fax")).clear();
    driver.findElement(By.name("fax")).sendKeys(contactData.getFax());
  }

  public void submitContactCreation() {
    driver.findElement(By.name("submit")).click();
    driver.findElement(linkText("home")).click();
  }

  public void initContactCreation(By home, String s) {
    driver.findElement(home).click();
    driver.findElement(linkText(s)).click();
  }
}
