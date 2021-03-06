package ru.pvg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
   Created Владимир  at 16:32  06.05.2019
*/
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getGroupName());
    type(By.name("group_header"), groupData.getGroupHeader());
    type(By.name("group_footer"), groupData.getGroupFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    driver.findElements(By.name("selected[]")).get(index).click(); //выбор элемента с номером index на странице
    // click(By.name("selected[]"));   //устарело - выбор первого элемента на странице
  }


  public void selectGroupById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click(); //выбор элемента с идентификатором id на странице

  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModificarion() {
    click(By.name("update"));
  }

  public void create(GroupData groupData) {
    initGroupCreation();
    fillGroupForm(groupData);
    submitGroupCreation();
    groupCache = null;
  }


  public void deleteGroup(int index) {
    selectGroup(index);
    deleteSelectedGroup();
    groupCache = null;
  }


  public void delete(GroupData groupToDelete) {
    selectGroupById(groupToDelete.getId());
    deleteSelectedGroup();
    groupCache = null;

  }

  public void modify(int index, GroupData group) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    submitGroupModificarion();
    groupCache = null;
  }

  public void modifyById(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModificarion();
    groupCache = null;
  }


  public boolean isThereAnyGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
     int id= Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String name = element.getText();
      String header = null;
      String footer= null;
      GroupData group = new GroupData().withId(id).withName(name).withHeader(header).withFooter(footer);
      groups.add(group);
    }
    return groups;
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      int id= Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String name = element.getText();
      String header = null;
      String footer= null;
      GroupData group = new GroupData().withId(id).withName(name).withHeader(header).withFooter(footer);
      groupCache.add(group);
    }
    return new Groups(groupCache);
  }


}
