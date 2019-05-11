package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

/*
   Created Владимир  at 17:02  03.05.2019 в новом branch
*/
public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoPage("groups");
    app.getGroupHelper().createGroup(new GroupData("test1", "test2 ", null));
    app.getNavigationHelper().gotoPage("home");
  }

          }
