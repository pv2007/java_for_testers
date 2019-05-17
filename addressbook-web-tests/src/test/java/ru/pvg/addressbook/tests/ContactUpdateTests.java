package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/*
   Created Владимир  at 9:36  09.05.2019
*/
public class ContactUpdateTests extends TestBase{

  @Test (enabled = true)
  public void testContactUpdate() throws Exception {
    app.goTo().gotoPage("home");
    // проверяем что есть хоть один контакт, если нет - создаем
    if (!app.getContactHelper().isThereAnyContact()) {
      app.getContactHelper().createContact(new ContactData(0,"z1", "z2", "z3", "z4", "z5", "6", "7", "z8", "z9", "z10", "z11", null), true);
      app.goTo().gotoPage("home");
    }
    // получить список контактов перед изменением
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size()-1 ;  // последний элемент

    app.getContactHelper().initContactUpdate(index);
    // параметры тестового контакта
    ContactData testContact = new ContactData(before.get(index).getId(), "Исправляю new1", "new2", "new3", "new4", "z5", "6", "7", "z8", "z9", "z10", "newz11", null);

    app.getContactHelper().fillContactForm(testContact, false);
    app.getContactHelper().submitContactUpdate();
    app.goTo().gotoPage("home");

    app.goTo().gotoPage("home");

    // получить список контактов после изменения
    List<ContactData> after = app.getContactHelper().getContactList();


    // проверяем что количество правильное
    Assert.assertEquals(after.size(), before.size());


    before.remove(index);
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
