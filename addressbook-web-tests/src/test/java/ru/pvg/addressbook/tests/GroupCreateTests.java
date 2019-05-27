package ru.pvg.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;


import java.io.*;
import java.util.ArrayList;
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
  public Iterator<Object[]> validGroups() throws IOException {
    //открываем файл источник
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
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

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().gotoPage("groups");
    //получить set до добавления новой записи
    Groups before = app.group().all();
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

  @Test (enabled = false)
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
