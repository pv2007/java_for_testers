package ru.pvg.addressbook.tests;

import com.sun.source.doctree.SeeTree;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/*
   Created Владимир  at 18:07  07.05.2019
*/
public class GroupModifTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().gotoPage("groups");
    // проверка предусловия: есть ли хоть одна группа для модификации. Если нет - создать
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter(null));
      app.goTo().gotoPage("groups");
    }
  }


  @Test
  public void testGroupModification(){
    Set<GroupData> before = app.group().all();
    GroupData groupToModif = before.iterator().next();
    GroupData group = new GroupData()
            .withId(groupToModif.getId()).withName("test NEW 123").withHeader("new test234").withFooter("new test3");
    app.group().modifyById(group);
    app.goTo().gotoPage("groups");
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    //получаем id элемента, который мы модифицировали
    // и присваиваем его id в набор group
    //group.withId(before.get(before.size() - 1).getId());

    // меняем элемент before.size()-1  на элемент с новыми данными
    before.remove(groupToModif);
    before.add(group);

    // только для списков требуется сортировка
    //начиная с Java 8 у списков появился метод sort в который в качестве параметра передается метод - компаратор
    // создаем лямбда функцию - компаратор
//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //соритируем оба списка
//    before.sort(byId);
//    after.sort(byId);

    //сравниваем before и after как списки
    Assert.assertEquals(before, after);

    app.goTo().gotoPage("home");
  }


}
