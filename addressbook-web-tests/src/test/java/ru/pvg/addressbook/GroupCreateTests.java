package ru.pvg.addressbook;

import org.testng.annotations.Test;

/*
   Created Владимир  at 17:02  03.05.2019
*/
public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage("groups");
    initGroupCreation();
    fillGroupForm(new GroupData("test1_1", "test2", "test3"));
    submitGroupCreation();
    gotoGroupPage("groups");
    gotoGroupPage("home");
  }

}
