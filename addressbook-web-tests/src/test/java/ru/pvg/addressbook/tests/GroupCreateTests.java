package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

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
    GroupData group = new GroupData("test2", "test2 ", null);
    app.getGroupHelper().createGroup(group);

    app.getNavigationHelper().gotoPage("groups");
    //получить set после добавления
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() + 1);


    //ищем максимальный идентификатор среди group
//    int max=0;
//    for (GroupData g: after) {
//      if (g.getId() > max) {
//        max = g.getId();
//      }
//    }

    // можно использовать Comparator с анонимным классом с методом compare
//    Comparator<? super GroupData> byId = new Comparator<GroupData>() {
//      @Override
//      public int compare(GroupData o1, GroupData o2) {
//        return Integer.compare(o1.getId(), o2.getId());
//      }
//    };
    //вычисление в множестве after элемента с максимальным id используя новый метод byId
//     int max1 = after.stream().max(byId).get().getId();


    // C Java 8 можно использовать лямбда функции
    //Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());

    //вычисление в множестве after элемента с максимальным id
    //int max2 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    // присвиваем максимальный id созданной записи и добавляем в Множество
    //group.setId(max);
    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId(););

    //используем лямбда функцию внутри метода setId()
    group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());

    before.add(group);
    //преобразуем коллекции before и after в Множества и сравниваем целиком
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    app.getNavigationHelper().gotoPage("home");
  }

}
