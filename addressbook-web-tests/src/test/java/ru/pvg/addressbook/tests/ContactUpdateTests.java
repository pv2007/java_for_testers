package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;

/*
   Created Владимир  at 9:36  09.05.2019
*/
public class ContactUpdateTests extends TestBase{

  @Test
  public void testContactUpdate() throws Exception {
    app.getNavigationHelper().gotoPage("home");
    app.getContactHelper().initContactUpdate();
    app.getContactHelper().fillContactForm(new ContactData("new1", "new2", "new3", "new4", "z5", "6", "7", "newz8", "newz9", "newz10", "newz11", null), false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().gotoPage("home");
  }


}
