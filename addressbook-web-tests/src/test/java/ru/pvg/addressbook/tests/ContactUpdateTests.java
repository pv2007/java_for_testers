package ru.pvg.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.Contacts;
import ru.pvg.addressbook.model.Groups;

import java.io.File;

/*
   Created Владимир  at 9:36  09.05.2019
*/
public class ContactUpdateTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoPage("home");
    // проверяем что есть хоть один контакт, если нет - создаем
    if (!app.contact().isThereAnyContact()) {
      Groups groups = app.db().groups();
      // параметры нового контакта
      ContactData testContact = new ContactData()
              .withFirstName("z1").withMiddleName( "z2").withLastName("z3").withNickName("z4").withCompany("z5").withTitle("6").withAddress("7").withHomePhone("z8").withMobilePhone("z9").withWorkPhone("z10").withFax("z11").inGroup(groups.iterator().next()); //.withGroup("test100");
      app.goTo().gotoPage("home");
      app.contact().create(testContact, true);
    }


  }
  @Test (enabled = true)
  public void testContactUpdate() throws Exception {

    Groups groups = app.db().groups();

    // получить список контактов перед изменением
    Contacts before = app.contact().all();

    ContactData contactToUpdate = before.iterator().next(); //выбирается  произвольный элемент множества

    // параметры тестового контакта
    File photo = new File("src/test/resources/mapbp.jpg");

    // задаем новые параметры тестового контакта
    ContactData testContact = new ContactData()
            .withId(contactToUpdate.getId()).withFirstName("Исправляю на new1").withMiddleName( "new2").withLastName("new3").withNickName("new4").withCompany("new5").withTitle("new6")
            .withAddress("7").withHomePhone("z8").withMobilePhone("z9").withWorkPhone("z10").withFax("z11").withPhoto(photo).inGroup(groups.iterator().next()); //.withGroup("test100");

    app.contact().updateContactById(contactToUpdate.getId());

    app.contact().fillContactForm(testContact, false);
    app.contact().submitContactUpdate();
    app.goTo().gotoPage("home");

    // получить список контактов после изменения
    Contacts after = app.contact().all();

    // проверяем что количество одинаковое
    Assert.assertEquals(after.size(), before.size());

    //сравниваем before и after как списки
    //полная запись без сокращения за счет импорта статичных методов
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(contactToUpdate).withAdded(testContact)));
  }


}
