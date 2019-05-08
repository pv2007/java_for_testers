package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.GroupData;

/*
   Created Владимир  at 20:28  07.05.2019
*/
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void submitContactCreation() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getContactFirstName());
    type(By.name("middlename"), contactData.getContactMiddleName());
    type(By.name("lastname"), contactData.getcontactLastName());
    type(By.name("nickname"), contactData.getContactNickName());
    type(By.name("title"), contactData.getContactTitle());
    type(By.name("company"), contactData.getContactCompany());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.name("delete"));
  }

  public void selectContact() {
    click(By.id("1"));
  }

  public void initContactModification() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Fname'])[1]/following::img[2]"));
  }

  public void submitContactModificarion() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
  }

}
