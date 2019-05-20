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
    if (!app.contact().isThereAnyContact()) {
      // параметры нового контакта
      ContactData testContact = new ContactData()
              .withFirstName("z1").withMiddleName( "z2").withLastName("z3").withNickName("z4").withCompany("z5").withTitle("6").withAddress("7").withHomePhone("z8").withMobilePhone("z9").withWorkPhone("z10").withFax("z11").withGroup("test100");
      app.goTo().gotoPage("home");
    }
    // получить список контактов перед добавлением
    List<ContactData> before = app.contact().all();

    app.contact().initContactDelete(before.size()-1);
    app.contact().submitContactDelete();

     if (!app.contact().isThereAnyContact()) {
      return;
    }

    //app.goTo().gotoPage("home");
    //app.contact().initContactDelete(0);

    // получить список контактов после удаления
    List<ContactData> after = app.contact().all();


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
