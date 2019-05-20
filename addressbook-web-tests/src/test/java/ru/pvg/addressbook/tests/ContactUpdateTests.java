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
    if (!app.contact().isThereAnyContact()) {
      // параметры нового контакта
      ContactData testContact = new ContactData()
              .withFirstName("z1").withMiddleName( "z2").withLastName("z3").withNickName("z4").withCompany("z5").withTitle("6").withAddress("7").withHomePhone("z8").withMobilePhone("z9").withWorkPhone("z10").withFax("z11").withGroup("test100");
      app.goTo().gotoPage("home");
    }
    // получить список контактов перед изменением
    List<ContactData> before = app.contact().all();
    int index = before.size()-1 ;  // последний элемент

    app.contact().initContactUpdate(index);
    // параметры тестового контакта
    ContactData testContact = new ContactData()
            .withId(before.get(index).getId()).withFirstName("Исправляю new1").withMiddleName( "new2").withLastName("new3").withNickName("new4").withCompany("new5").withTitle("new6").withAddress("7").withHomePhone("z8").withMobilePhone("z9").withWorkPhone("z10").withFax("z11").withGroup("test100");
    app.contact().fillContactForm(testContact, false);
    app.contact().submitContactUpdate();
    app.goTo().gotoPage("home");

    app.goTo().gotoPage("home");

    // получить список контактов после изменения
    List<ContactData> after = app.contact().all();


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
