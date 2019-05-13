package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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

    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test1", "new test2", "new test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModificarion();
    app.getNavigationHelper().gotoPage("groups");
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    // меняем элемент before.size()-1  на то, что поменяли
    before.remove(before.size() - 1);
    before.add(group);
    //преобразуем коллекции before и after в Множества и сравниваем целиком
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    app.getNavigationHelper().gotoPage("home");
  }
}
