package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/*
   Created Владимир  at 17:02  03.05.2019 в новом branch
*/
public class GroupCreateTests extends TestBase {

  @Test (enabled = true)
  public void testGroupCreation() throws Exception {
    app.goTo().gotoPage("groups");
    //получить set до добавления новой записи
    Groups before = app.group().all();
    //создать набор с параметрами новой записи (но без поля id ! - т.к. мы его не знаем)
    GroupData group = new GroupData().withName("test256");
    app.group().create(group);
    app.goTo().gotoPage("groups");
    assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));

    //получить set после добавления
    Groups after = app.group().all();

    //сраввниваем списки before и after
    // сравнение из пакета hamcrest
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt( (g) -> g.getId() ).max().getAsInt()))));

    app.goTo().gotoPage("home");
  }

  @Test (enabled = true)
  // негативный тест - не создается группа с ошибкой в имени и список остается такой же
  public void testBadGroupCreation() throws Exception {
    app.goTo().gotoPage("groups");
    //получить set до добавления новой записи
    Groups before = app.group().all();
    //создать набор с параметрами новой записи (но без поля id ! - т.к. мы его не знаем)
    GroupData group = new GroupData().withName("test256'");
    app.group().create(group);
    app.goTo().gotoPage("groups");
    //проверяем количество элементов через getGroupCount() ДО вызова сета after
    // это позволяет исключить загрузку объектов, если их количество не равно
    assertThat(app.group().getGroupCount(), equalTo(before.size()));

    //app.goTo().gotoPage("groups");
    //получить set after после добавления
    Groups after = app.group().all();

    // группы до и после одинаковы
    assertThat(after, equalTo(before));

    app.goTo().gotoPage("home");
  }



}
