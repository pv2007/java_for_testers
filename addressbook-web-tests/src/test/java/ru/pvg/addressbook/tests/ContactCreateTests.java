package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;


/*
   Created Владимир  at 11:26  08.05.2019
*/
public class ContactCreateTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoPage("home");
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("z1", "z2", "z3", "z4", "z5", "6", "7", "z8", "z9", "z10", "z11", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoPage("home");
  }


}

