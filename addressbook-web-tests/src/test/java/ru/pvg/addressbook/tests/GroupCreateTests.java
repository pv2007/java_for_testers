package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

/*
   Created Владимир  at 17:02  03.05.2019 в новом branch
*/
public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage("groups");
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Моя группа 1", null, null));
    app.getGroupHelper().submitGroupCreation();
    app.getNavigationHelper().gotoGroupPage("groups");
    app.getNavigationHelper().gotoGroupPage("home");
  }

          }
