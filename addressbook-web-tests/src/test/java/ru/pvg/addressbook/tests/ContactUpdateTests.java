package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.GroupData;

/*
   Created Владимир  at 9:36  09.05.2019
*/
public class ContactUpdateTests extends TestBase{

  @Test
  public void testContactUpdate() throws Exception {
    app.getNavigationHelper().gotoPage("home");
    // проверяем что есть хоть один контакт, если нет - создаем
    if (!app.getContactHelper().isThereAnyContact()) {
      app.getContactHelper().createContact(new ContactData("z1", "z2", "z3", "z4", "z5", "6", "7", "z8", "z9", "z10", "z11", null), true);
      app.getNavigationHelper().gotoPage("home");
    }
    app.getContactHelper().initContactUpdate();
    app.getContactHelper().fillContactForm(new ContactData("Тест изменения new1", "new2", "new3", "new4", "z5", "6", "7", "z8", "z9", "z10", "newz11", null), false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().gotoPage("home");
  }


}
