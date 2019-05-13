package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/*
   Created Владимир  at 17:02  03.05.2019 в новом branch
*/
public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoPage("groups");
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", "test2 ", null);
    app.getGroupHelper().createGroup(group);

    app.getNavigationHelper().gotoPage("groups");
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() + 1);


    //ищем максимальный идентификатор среди group
    int max=0;
    for (GroupData g: after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    // присвиваем максимальный id созданной записи и добавляем в Множество
    group.setId(max);
    before.add(group);
    //преобразуем коллекции before и after в Множества и сравниваем целиком
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    app.getNavigationHelper().gotoPage("home");
  }

}
