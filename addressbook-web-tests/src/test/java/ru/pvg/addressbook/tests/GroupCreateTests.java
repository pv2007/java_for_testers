package ru.pvg.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;


import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/*
   Created Владимир  at 17:02  03.05.2019 в новом branch
*/
public class GroupCreateTests extends TestBase {



  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    //открываем файл источник
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();   // читаем одну строку
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);   // запись (List<GroupData>) - явное преобразование Object
      // который возвращает fromXML(), в тип GroupData
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    //открываем файл источник
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();   // читаем одну строку
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = (List<GroupData>) gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());   // запись (List<GroupData>) - явное преобразование Object
      // который возвращает fromJson(), в тип GroupData
      //TypeToken...   вместо List<GroupData>.class
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {

    //получить set до добавления новой записи
    Groups before = app.db().groups();

    app.goTo().gotoPage("groups");
    app.group().create(group);
    app.goTo().gotoPage("groups");

    //получить set после добавления
    Groups after = app.db().groups();

    assertThat(after.size(), equalTo(before.size() + 1));

    //сраввниваем списки before и after
    // сравнение из пакета hamcrest
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt( (g) -> g.getId() ).max().getAsInt()))));

    app.goTo().gotoPage("home");
  }

  @Test (enabled = false)
  // негативный тест - не создается группа с ошибкой в имени и список остается такой же
  public void testBadGroupCreation() throws Exception {
    app.goTo().gotoPage("groups");
    //получить set до добавления новой записи
    Groups before = app.db().groups();
    //создать набор с параметрами новой записи (но без поля id ! - т.к. мы его не знаем)
    GroupData group = new GroupData().withName("test256'");
    app.group().create(group);
    app.goTo().gotoPage("groups");
    //проверяем количество элементов через getGroupCount() ДО вызова сета after
    // это позволяет исключить загрузку объектов, если их количество не равно
    assertThat(app.group().getGroupCount(), equalTo(before.size()));

    //app.goTo().gotoPage("groups");
    //получить set after после добавления
    Groups after = app.db().groups();

    // группы до и после одинаковы
    assertThat(after, equalTo(before));

    app.goTo().gotoPage("home");
  }



}
