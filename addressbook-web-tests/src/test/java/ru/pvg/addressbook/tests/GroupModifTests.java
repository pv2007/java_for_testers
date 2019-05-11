package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

/*
   Created Владимир  at 18:07  07.05.2019
*/
public class GroupModifTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoPage("groups");
    // проверка предусловия: есть ли хоть одна группа для модификации. Если нет - создать
    if (!app.getGroupHelper().isThereAnyGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2 ", null));
      app.getNavigationHelper().gotoPage("groups");
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "Новая группа тест33"));
    app.getGroupHelper().submitGroupModificarion();
    app.getNavigationHelper().gotoPage("home");
  }
}
