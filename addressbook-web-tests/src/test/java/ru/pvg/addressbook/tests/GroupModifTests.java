package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

import java.util.Comparator;
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
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test123", "new test234", "new test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModificarion();
    app.getNavigationHelper().gotoPage("groups");
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    //получаем id элемента, который мы модифицировали
    // и присваиваем его id в набор group
    //group.setId(before.get(before.size() - 1).getId());

    // меняем элемент before.size()-1  на элемент с новыми данными
    before.remove(before.size() - 1);
    before.add(group);

    //начиная с Java 8 у списков появился метод sort в который в качестве параметра передается метод - компаратор
    // создаем лямбда функцию - компаратор
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //соритируем оба списка
    before.sort(byId);
    after.sort(byId);

    //сравниваем before и after как списки
    Assert.assertEquals(before, after);

    app.getNavigationHelper().gotoPage("home");
  }
}
