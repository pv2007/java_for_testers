package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.Contacts;

/*
   Created Владимир  at 11:29  20.05.2019
*/
public class ContactPhoneTests extends TestBase {

  @Test (enabled = false)
  public void testContactPhones() {
    app.goTo().gotoPage("home");
    ContactData contact = app.contact().all().iterator().next(); //выбирается  произвольный элемент множества
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


  }

}
