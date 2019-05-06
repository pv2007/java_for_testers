package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupInABookData;

/*
   Created Владимир  at 17:02  03.05.2019
*/
public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage("groups");
    app.initGroupCreation();
    app.fillGroupForm(new GroupInABookData("Моя группа 1", "Моя группа тест2", "Моя группа тест33"));
    app.submitGroupCreation();
    app.gotoGroupPage("groups");
    app.gotoGroupPage("home");
  }

}
