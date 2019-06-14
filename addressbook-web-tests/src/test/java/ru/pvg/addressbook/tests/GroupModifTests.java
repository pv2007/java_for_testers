package ru.pvg.addressbook.tests;

import com.sun.source.doctree.SeeTree;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/*
   Created Владимир  at 18:07  07.05.2019
*/
public class GroupModifTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().gotoPage("groups");
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter(null));
      app.goTo().gotoPage("groups");
    }

  }


  @Test (enabled = true)
  public void testGroupModification(){
    String s1 = System.getProperty("verifyUI");

    Groups before = app.db().groups();
    GroupData groupToModif = before.iterator().next();

    GroupData group = new GroupData()
            .withId(groupToModif.getId()).withName("test NEW 456").withHeader("new test789").withFooter("new test3");
    app.goTo().gotoPage("groups");
    app.group().modifyById(group);
    app.goTo().gotoPage("groups");
    Groups after = app.db().groups();
    MatcherAssert.assertThat(after.size(), CoreMatchers.equalTo(before.size()));


    //сравниваем before и after как списки
    //полная запись без сокращения без импорта статичных методов
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(groupToModif).withAdded(group)));

    app.goTo().gotoPage("home");

    verifyGroupListInUI();
  }




}
