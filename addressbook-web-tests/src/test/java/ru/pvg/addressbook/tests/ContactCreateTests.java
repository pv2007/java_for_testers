package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;

/*
   Created Владимир  at 21:07  07.05.2019
*/
public class ContactCreateTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage("home");
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Петр", "Иванович", "Сидоров","Петро","Директор", "ООО 'Одуванчик'"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoGroupPage("groups");
    app.getNavigationHelper().gotoGroupPage("home");
  }

}
