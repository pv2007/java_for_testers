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

  @Test
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


    //используем лямбда функцию внутри метода withId() для поиска максимального id
    // и присваиваем его в набор group
    // group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId(););
    // group.withId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());

    //получаем максимальный идентификатор id в множестве преобразовав в поток номеров mapToInt()
    // и присваиваем его в поле  id созданной группы
    group.withId(after.stream().mapToInt( (g) -> g.getId() ).max().getAsInt());


    // ! только для списков - сортируем перед сравнением
    //начиная с Java 8 у списков появился метод sort
    // создаем лямбда функцию
    // ПЕРВЫЙ ВАРИАНТ - старая запись через лямбда-выражение, ВТОРОЙ вариант новый через  метод comparingInt
    //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    //соритируем оба списка
//    before.sort(byId);
//    after.sort(byId);

    //сраввниваем списки before и after
    // сравнение из пакета hamcrest
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt( (g) -> g.getId() ).max().getAsInt()))));

    app.goTo().gotoPage("home");
  }

  @Test
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
