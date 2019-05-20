package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;

import java.util.List;

/*
   Created Владимир  at 11:29  20.05.2019
*/
public class ContactPhoneTests extends TestBase {

  @Test (enabled = false)
  public void testContactPhones() {
    app.goTo().gotoPage("home");

    List<ContactData> before = app.contact().all();
    int index = before.size()-1 ;  // последний элемент
    //ContactData contact = app.contact().
    app.contact().initContactUpdate(index);

  }

}
