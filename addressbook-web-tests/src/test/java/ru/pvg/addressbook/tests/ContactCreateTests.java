package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.Contacts;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/*
   Created Владимир  at 11:26  08.05.2019
*/
public class ContactCreateTests extends TestBase {

  @Test (enabled = true)
  public void testContactCreation() throws Exception {

    app.goTo().gotoPage("home");
    // проверка что есть хоть одна группа, если нет - то ее создаем (группа указывается при создании тестового контакта)
    if (!app.group().isThereAnyGroup()) {
      app.goTo().gotoPage("groups");
      app.group().create(new GroupData().withName("test 100").withHeader("test200"));
      app.goTo().gotoPage("home");
    }

    Groups groups = app.db().groups();
    // параметры тестового контакта
    File photo = new File("src/test/resources/mapbp.jpg");

    ContactData testContact = new ContactData()
            .withFirstName("new z332").withMiddleName( "z2").withLastName("z333").withNickName("z4")
            .withCompany("z5").withTitle("6").withAddress("7").withHomePhone("z8").withMobilePhone("z9")
            .withWorkPhone("z10").withFax("z11").withPhoto(photo).inGroup(groups.iterator().next()); //.withGroup("test100")

    // получить список контактов перед добавлением
    Contacts before = app.contact().all();

    app.contact().create(testContact, true);
    app.goTo().gotoPage("home");

    // получить список контактов после добавления
    Contacts after = app.contact().all();
    // проверяем что количество правильное
    Assert.assertEquals(after.size(), before.size() + 1);

    // проверяем что элементы одинаковые
    assertThat(after, equalTo(
            before.withAdded(testContact.withId(after.stream().mapToInt( (g) -> g.getId() ).max().getAsInt()))));

  }
    // проверить куда указывает рабочая деректория
//  @Test
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsoluteFile());
//    File photo = new File("src/test/resources/mapbp.jpg");
//    System.out.println(photo.getAbsoluteFile());
//    System.out.println(photo.exists());
//  }

}

