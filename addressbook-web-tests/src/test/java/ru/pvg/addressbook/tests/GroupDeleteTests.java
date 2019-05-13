package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

import java.util.List;

/*
   Created Владимир  at 12:41  04.05.2019
*/
public class GroupDeleteTests extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().gotoPage("groups");
    // проверка предусловия: есть ли хоть одна группа для удаления. Если нет - создать
    if (!app.getGroupHelper().isThereAnyGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2 ", null));
      app.getNavigationHelper().gotoPage("groups");
    }
    //получение коллекции данных group до удаления
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getNavigationHelper().gotoPage("groups");
    //получение коллекции данных group после удаления
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //сравнить количество записей коллекции ДО и ПОСЛЕ удаления
    Assert.assertEquals(after.size(), before.size() - 1);

    //удаляем последний элемент из коллекции before
    before.remove(before.size() - 1);

    //сравниваем коллекции before и after по-элементно
//    for (int i = 0; i < after.size(); i++) {
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    
    //сравниваем коллекции before и after целиком
    Assert.assertEquals(before, after);

    app.getNavigationHelper().gotoPage("home");
  }

}
