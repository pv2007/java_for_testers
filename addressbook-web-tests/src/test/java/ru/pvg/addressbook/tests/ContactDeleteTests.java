package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
   Created Владимир  at 9:57  09.05.2019
*/
public class ContactDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
  }

    @Test (enabled = true)
  public void testContactDelete() throws Exception {

      app.goTo().gotoPage("home");
      // проверяем что есть хоть один контакт, если нет - создаем
      if (!app.contact().isThereAnyContact()) {
        // параметры нового контакта
        ContactData testContact = new ContactData()
                .withFirstName("z1").withMiddleName( "z2").withLastName("z3").withNickName("z4").withCompany("z5").withTitle("6").withAddress("7").withHomePhone("z8").withMobilePhone("z9").withWorkPhone("z10").withFax("z11").withGroup("test100");
        app.goTo().gotoPage("home");
        app.contact().create(testContact, true);
      }

    // получить список контактов перед удалением
    Contacts before = app.contact().all();

    ContactData contactToDelete = before.iterator().next(); //выбирается  произвольный элемент множества

    app.contact().selectContactById(contactToDelete.getId());
    app.contact().submitContactDelete();

     if (app.contact().isThereAnyContact()){
       app.goTo().gotoPage("home");
     }

    // получить список контактов после удаления
    Contacts after = app.contact().all();

    // проверяем что количество правильное
    Assert.assertEquals(after.size(), before.size()-1);

    // проверяем что элементы одинаковые
    assertThat(after, equalTo(before.without(contactToDelete)));

    app.goTo().gotoPage("home");
  }


}
