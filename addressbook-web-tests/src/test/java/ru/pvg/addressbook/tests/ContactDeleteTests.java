package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;

import static org.openqa.selenium.By.linkText;

/*
   Created Владимир  at 9:57  09.05.2019
*/
public class ContactDeleteTests extends TestBase {
  @Test
  public void testContactDelete() throws Exception {
    app.getContactHelper().initContactDelete(linkText("home"), "14");
    app.getContactHelper().submitContactDelete();
  }


}
