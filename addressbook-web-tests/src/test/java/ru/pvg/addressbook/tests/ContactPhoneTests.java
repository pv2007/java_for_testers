package ru.pvg.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.Contacts;

import java.sql.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/*
   Created Владимир  at 11:29  20.05.2019
*/
public class ContactPhoneTests extends TestBase {

  @Test (enabled = true)
  public void testContactPhones() {
    app.goTo().gotoPage("home");
    ContactData contact = app.contact().all().iterator().next(); //выбирается  произвольный элемент множества
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s) ->!s.equals(""))      //преобразовать в поток stream и удалить пустые элементы анонимной функцией
            .map(ContactPhoneTests::cleaned)            //применить метод cleaned для всех элементов потока
            .collect(Collectors.joining("\n"));   //склеить элементы символом \n
  }

  // очистка номера от посторонних символов пробел, - ( )
  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
