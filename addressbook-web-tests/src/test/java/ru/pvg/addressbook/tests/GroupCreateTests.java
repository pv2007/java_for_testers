package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/*
   Created Владимир  at 17:02  03.05.2019 в новом branch
*/
public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoPage("groups");
    //получить set до добавления новой записи
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //создать набор с параметрами новой записи (но без поля id ! - т.к. мы его не знаем)
    GroupData group = new GroupData("test100", "test2 ", null);
    app.getGroupHelper().createGroup(group);

    app.getNavigationHelper().gotoPage("groups");
    //получить set после добавления
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() + 1);

    //используем лямбда функцию внутри метода setId() для поиска максимального id
    // и присваиваем его в набор group
    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId(););
    group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    // добавляем в before новый элемент
    before.add(group);

    //начиная с Java 8 у списков появился метод sort
    // создаем лямбда функцию
    // ПЕРВЫЙ ВАРИАНТ - старая запись через лямбда-выражение, ВТОРОЙ вариант новый через  метод comparingInt
    //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    //соритируем оба списка
    before.sort(byId);
    after.sort(byId);

    //сраввниваем списки before и after, т.к. они одинаково отсортированы
    Assert.assertEquals(before,after);

    app.getNavigationHelper().gotoPage("home");
  }

}
