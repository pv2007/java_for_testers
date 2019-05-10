package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import static org.openqa.selenium.By.linkText;

/*
   Created Владимир  at 9:36  09.05.2019
*/
public class ContactUpdateTests extends TestBase{

  @Test
  public void testContactUpdate() throws Exception {
    app.getContactHelper().initContactUpdate(linkText("home"), "7");
    app.getContactHelper().fillContactForm(new ContactData("new1", "new2", "new3", "new4", "z5", "6", "7", "newz8", "newz9", "newz10", "newz11", null), false);
    app.getContactHelper().submitContactUpdate();
  }


}
