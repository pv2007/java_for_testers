package ru.pvg.addressbook;

import org.testng.annotations.Test;

/*
   Created Владимир  at 12:41  04.05.2019
*/
public class GroupDeleteTests extends TestBase{

  @Test
  public void testGroupDelete() throws Exception {
    gotoGroupPage("groups");
    selectGroup();
    deleteSelectedGroup();
    gotoGroupPage("groups");
    gotoGroupPage("home");
  }

}
