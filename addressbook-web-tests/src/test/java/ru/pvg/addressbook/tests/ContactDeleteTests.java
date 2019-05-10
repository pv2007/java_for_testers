package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;

/*
   Created Владимир  at 9:57  09.05.2019
*/
public class ContactDeleteTests extends TestBase {
  @Test
  public void testContactDelete() throws Exception {
    app.getNavigationHelper().gotoPage("home");
    app.getContactHelper().initContactDelete("selected[]");
    app.getContactHelper().submitContactDelete();
    app.getNavigationHelper().gotoPage("home");
  }


}
