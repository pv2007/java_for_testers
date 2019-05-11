package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;

/*
   Created Владимир  at 9:57  09.05.2019
*/
public class ContactDeleteTests extends TestBase {
  @Test
  public void testContactDelete() throws Exception {
    app.getNavigationHelper().gotoPage("home");
    // проверяем что есть хоть один контакт, если нет - создаем
    if (!app.getContactHelper().isThereAnyContact()) {
      app.getContactHelper().createContact(new ContactData("z1", "z2", "z3", "z4", "z5", "6", "7", "z8", "z9", "z10", "z11", null), true);
      app.getNavigationHelper().gotoPage("home");
    }
    app.getContactHelper().initContactDelete();
    app.getContactHelper().submitContactDelete();
    app.getNavigationHelper().gotoPage("home");
  }


}
