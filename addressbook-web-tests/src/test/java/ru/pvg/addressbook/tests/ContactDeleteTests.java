package ru.pvg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/*
   Created Владимир  at 9:57  09.05.2019
*/
public class ContactDeleteTests extends TestBase {

  @Test (enabled = true)
  public void testContactDelete() throws Exception {
    app.goTo().gotoPage("home");
    // проверяем что есть хоть один контакт, если нет - создаем
    if (!app.getContactHelper().isThereAnyContact()) {
      app.getContactHelper().createContact(new ContactData("z1", "z2", "z3", "z4", "z5", "6", "7", "z8", "z9", "z10", "z11", null), true);
      app.goTo().gotoPage("home");
    }
    // получить список контактов перед добавлением
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().initContactDelete(before.size()-1);
    app.getContactHelper().submitContactDelete();

     if (!app.getContactHelper().isThereAnyContact()) {
      return;
    }

    //app.goTo().gotoPage("home");
    //app.getContactHelper().initContactDelete(0);

    // получить список контактов после удаления
    List<ContactData> after = app.getContactHelper().getContactList();


    // проверяем что количество правильное
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(before.size() - 1);

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





    app.goTo().gotoPage("home");
  }


}
