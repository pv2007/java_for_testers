package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

/*
   Created Владимир  at 18:07  07.05.2019
*/
public class GroupModifTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage("groups");
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Новая группа 1", "Новая группа тест2", "Новая группа тест33"));
    app.getGroupHelper().submitGroupModificarion();
    app.getNavigationHelper().gotoGroupPage("groups");
    app.getNavigationHelper().gotoGroupPage("home");
  }
}
