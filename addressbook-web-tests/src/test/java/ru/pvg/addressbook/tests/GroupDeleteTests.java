package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

/*
   Created Владимир  at 12:41  04.05.2019
*/
public class GroupDeleteTests extends TestBase{

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().gotoPage("groups");
    // проверка предусловия: есть ли хоть одна группа для удаления. Если нет - создать
    if (!app.getGroupHelper().isThereAnyGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2 ", null));
      app.getNavigationHelper().gotoPage("groups");
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getNavigationHelper().gotoPage("groups");
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);

    app.getNavigationHelper().gotoPage("home");
  }

}
