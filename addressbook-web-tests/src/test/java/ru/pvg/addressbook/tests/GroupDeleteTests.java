package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;

/*
   Created Владимир  at 12:41  04.05.2019
*/
public class GroupDeleteTests extends TestBase{

  @Test
  public void testGroupDelete() throws Exception {
    app.gotoGroupPage("groups");
    app.selectGroup();
    app.deleteSelectedGroup();
    app.gotoGroupPage("groups");
    app.gotoGroupPage("home");
  }

}
