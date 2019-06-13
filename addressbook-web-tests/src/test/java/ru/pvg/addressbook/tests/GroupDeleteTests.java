package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;

import javax.swing.*;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/*
   Created Владимир  at 12:41  04.05.2019
*/
public class GroupDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoPage("groups");
    // проверка предусловия: есть ли хоть одна группа для удаления. Если нет - создать
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2"));
      app.goTo().gotoPage("groups");
    }
  }


  @Test (enabled = true)
  public void testGroupDelete() throws Exception {
    //получение коллекции данных group до удаления (используется groupCache, полученный в начале)
    Groups before = app.db().groups();
    GroupData groupToDelete = before.iterator().next(); //выбирается  произвольный элемент множества

    app.group().delete(groupToDelete);
    app.goTo().gotoPage("groups");
    //получение коллекции данных group после удаления
    Groups after = app.db().groups();

    //сравнить количество записей коллекции ДО и ПОСЛЕ удаления
    assertThat(after.size(), equalTo(before.size() - 1));

    //удаляем последний элемент из коллекции before
    //    before.remove(groupToDelete);


    //сравниваем коллекции before и after целиком
    //  MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(groupToDelete))); - сокращаем, делая вызов статических функций
    assertThat(after, equalTo(before.without(groupToDelete)));
    app.goTo().gotoPage("home");
  }



}
