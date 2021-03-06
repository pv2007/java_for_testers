package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.Contacts;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;

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
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("middlename"),contactData.getMiddleName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("nickname"),contactData.getNickName());
    type(By.name("title"),contactData.getTitle());
    type(By.name("company"),contactData.getCompany());
    type(By.name("home"),contactData.getHomePhone());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("fax"),contactData.getFax());
    attach(By.name("photo"),contactData.getPhoto());


    // присвоение полю из выпадающегог списка Group возможно только при создании contact
    // при изменении этого поля нет на экране (его нельзя изменить
    if (creation) {
      if (contactData.getGroups().size() != 0) {
        //проверяем что указана только одна группа, т.к. две группы нельзя присвоить
        Assert.assertTrue(contactData.getGroups().size() == 1);
        //присвоить значение атрибуту Group только если он указан
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getGroupName());
      }
    } else {
      //выключено для скорости
      //Assert.assertFalse(isElementPresent(By.name("new_group"))); //проверка отсутствия поля Group на форме
    }
  }


  public void submitContactCreation() {
    driver.findElement(By.name("submit")).click();
  }

  public void initContactCreation() {
    driver.findElement(linkText("add new")).click();
  }

  public void update(ContactData contact) {
    // поиск списка элементов entry
    // из элемента index  поиск 3-го (0,1,2) селектора <td class="center" (кнопка  Edit (img) ) и ее нажатие
    driver.findElements(By.name("entry")).get(contact.getId()).findElements(By.cssSelector("td.center")).get(2).click();
  }

  public void updateContactById(int id) {
    // поиск элемента edit по идентификатору id
    //вариант 1 - по тексту ссылки (2 разных варианта записи)
//    driver.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
    //то же самое, но с подстановкой
//    driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();

    //вариант 2 - через путь xpath
//    driver.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();

    //вариант 3 - через путь xpath с подзапросами
//    driver.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();

    //вариант 4 - через поиск нужного чекбокса по id
    //потом переход на родительскую строку (вверх на 2 уровня)
    //получение содержимого всех ячеек строки
    //выбор ячейки 8 (порядковый номер 7) и поиск в ней тега a для клика
    WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

  }


  public void submitContactUpdate() {
    driver.findElement(By.name("update")).click();
  }

  public void delete(ContactData contact) {
    driver.findElements(By.name("selected[]")).get(contact.getId()).click(); //выбор элемента с номером index на странице
    // driver.findElement(By.name("selected[]")).click();
  }


  public void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[id='" + id + "']")).click(); //выбор элемента с индексом id на странице
    // driver.findElement(By.name("selected[]")).click();
  }


  public void submitContactDelete() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
    driver.switchTo().alert().accept();
  }

  public void create(ContactData contactData, boolean b) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitContactCreation();
  }

  public boolean isThereAnyContact() {
    return isElementPresent(By.id("maintable"));
  }


  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements1 = driver.findElements(By.name("entry")); //фиктивные запросы содержимого
    List<WebElement> elements2 = driver.findElements(By.name("entry")); //фиктивные запросы содержимого
    int s1 = elements1.size();  //фиктивные запросы содержимого
    int s2 = elements2.size();  //фиктивные запросы содержимого

    List<WebElement> elements = driver.findElements(By.name("entry"));
    int s = elements.size();

    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
      contacts.add(contact);
    }
    return contacts;
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements1 = driver.findElements(By.name("entry")); //фиктивные запросы содержимого
    List<WebElement> elements2 = driver.findElements(By.name("entry")); //фиктивные запросы содержимого
    int s1 = elements1.size();  //фиктивные запросы содержимого
    int s2 = elements2.size();  //фиктивные запросы содержимого

    List<WebElement> elements = driver.findElements(By.name("entry"));
    int s = elements.size();

    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      // String[] phones = cells.get(5).getText().split("\n");   //порезали строку телефонов
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails);
      contacts.add(contact);
    }
    return contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    updateContactById(contact.getId());
    String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    String address = driver.findElement(By.name("address")).getAttribute("value");
    String email = driver.findElement(By.name("email")).getAttribute("value");
    String email2 = driver.findElement(By.name("email2")).getAttribute("value");
    String email3 = driver.findElement(By.name("email3")).getAttribute("value");
    driver.navigate().back();

    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
