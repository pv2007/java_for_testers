package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

import java.util.List;

/*
   Created Владимир  at 12:41  04.05.2019
*/
public class GroupDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoPage("groups");
    // проверка предусловия: есть ли хоть одна группа для удаления. Если нет - создать
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2"));
      app.goTo().gotoPage("groups");
    }
  }


  @Test
  public void testGroupDelete() throws Exception {
    //получение коллекции данных group до удаления
    List<GroupData> before = app.group().list();
    int index = before.size() - 1 ;

    app.group().deleteGroup(index);
    app.goTo().gotoPage("groups");
    //получение коллекции данных group после удаления
    List<GroupData> after = app.group().list();

    //сравнить количество записей коллекции ДО и ПОСЛЕ удаления
    Assert.assertEquals(after.size(), before.size() - 1);

    //удаляем последний элемент из коллекции before
    before.remove(index);

    //сравниваем коллекции before и after по-элементно
//    for (int i = 0; i < after.size(); i++) {
//      Assert.assertEquals(before.get(i), after.get(i));
//    }

    //сравниваем коллекции before и after целиком
    Assert.assertEquals(before, after);

    app.goTo().gotoPage("home");
  }



}
