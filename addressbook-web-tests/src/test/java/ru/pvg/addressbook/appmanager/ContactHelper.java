package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pvg.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.linkText;

/*
   Created Владимир  at 16:14  08.05.2019
*/
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
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

    // присвоение полю из выпадающегог списка Group возможно только при создании contact
    // при изменении этого поля нет на экране (его нельзя изменить
    if (creation) {
          if (contactData.getGroup() != null) {
            //присвоить значение атрибуту Group только если он указан
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
          }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group"))); //проверка отсутствия поля Group на форме
    }
  }



  public void submitContactCreation() {
    driver.findElement(By.name("submit")).click();
   }

  public void initContactCreation() {
    driver.findElement(linkText("add new")).click();
  }

  public void initContactUpdate() {
    // поиск и редактирование первого сверху на странице элемента Edit (img)
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='z8z9z10'])[1]/following::img[2]")).click();
    //   driver.findElement(By.linkText("Edit")).click();
  }

  public void submitContactUpdate() {
    driver.findElement(By.name("update")).click();
    }

  public void initContactDelete(int index) {
    driver.findElements(By.name("selected[]")).get(index).click(); //выбор элемента с номером index на странице
    // driver.findElement(By.name("selected[]")).click();
  }

  public void submitContactDelete() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
    driver.switchTo().alert().accept();
  }

  public void createContact(ContactData contactData, boolean b) {
    initContactCreation();
    fillContactForm(contactData,true);
    submitContactCreation();
  }

  public boolean isThereAnyContact() {
    return isElementPresent(By.name("selected[]"));
  }


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.name("input")).getAttribute("value"));
      String lastName = element.findElements(By.cssSelector("td")).get(1).getText();
      String firstName = element.findElements(By.cssSelector("td")).get(2).getText();
      ContactData contact = new ContactData(id, firstName, null, lastName,null, null, null, null, null, null, null, null, null );
      contacts.add(contact);
    }
    return contacts;
  }

}
