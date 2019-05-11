package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.GroupData;


/*
   Created Владимир  at 11:26  08.05.2019
*/
public class ContactCreateTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoPage("home");
    // параметры тестового контакта
    ContactData testContact = new ContactData("z1", "z2", "z3", "z4", "z5", "6", "7", "z8", "z9", "z10", "z11", "test100");

    // проверка что есть хоть одна группа, если нет - то ее создаем (группа указывается при создании тестового контакта)
    app.getNavigationHelper().gotoPage("groups");
    if (!app.getGroupHelper().isThereAnyGroup()) {
      app.getGroupHelper().createGroup(new GroupData(testContact.getGroup(), "test2 ", null));
      app.getNavigationHelper().gotoPage("home");
    }
    app.getContactHelper().createContact(testContact, true);
    app.getNavigationHelper().gotoPage("home");
  }


}

