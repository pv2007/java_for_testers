package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


/*
   Created Владимир  at 11:26  08.05.2019
*/
public class ContactCreateTests extends TestBase {


  @Test (enabled = false)
  public void testContactCreation() throws Exception {
    app.goTo().gotoPage("home");
    // параметры тестового контакта
    ContactData testContact = new ContactData("new z111", "z2", "z333", "z4", "z5", "6", "7", "z8", "z9", "z10", "z11", "test100");
    // проверка что есть хоть одна группа, если нет - то ее создаем (группа указывается при создании тестового контакта)
    if (!app.group().isThereAnyGroup()) {
      app.goTo().gotoPage("groups");
      app.group().create(new GroupData(testContact.getGroup(), "test2 ", null));
      app.goTo().gotoPage("home");
    }
    // получить список контактов перед добавлением
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(testContact, true);
    app.goTo().gotoPage("home");

    // получить список контактов после добавления
    List<ContactData> after = app.getContactHelper().getContactList();
    int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    testContact.setId(max);
    // проверяем что количество правильное
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(testContact);

    //начиная с Java 8 у списков появился метод sort
    // создаем лямбда функцию
    // ПЕРВЫЙ ВАРИАНТ - старая запись через лямбда-выражение, ВТОРОЙ вариант новый через  метод comparingInt
    //Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    //соритируем оба списка
    before.sort(byId);
    after.sort(byId);

    // проверяем что элементы одинаковые
    Assert.assertEquals(after, before);


  }


}

