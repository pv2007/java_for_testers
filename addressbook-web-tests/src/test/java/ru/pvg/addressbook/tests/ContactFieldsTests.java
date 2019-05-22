package ru.pvg.addressbook.tests;


import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
   Created Владимир  at 11:29  20.05.2019
*/
public class ContactFieldsTests extends TestBase {

  @Test (enabled = true)
  public void testContactPhones() {
    app.goTo().gotoPage("home");
    ContactData contact = app.contact().all().iterator().next(); //выбирается  произвольный элемент множества
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  @Test (enabled = true)
  public void testContactAddress() {
    app.goTo().gotoPage("home");
    ContactData contact = app.contact().all().iterator().next(); //выбирается  произвольный элемент множества
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  @Test (enabled = true)
  public void testContactEmails() {
    app.goTo().gotoPage("home");
    ContactData contact = app.contact().all().iterator().next(); //выбирается  произвольный элемент множества
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) ->!s.equals(""))      //преобразовать в поток stream и удалить пустые элементы анонимной функцией
            .collect(Collectors.joining("\n"));   //склеить элементы символом \n
  }


  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s) ->!s.equals(""))      //преобразовать в поток stream и удалить пустые элементы анонимной функцией
            .map(ContactFieldsTests::cleaned)            //применить метод cleaned для всех элементов потока
            .collect(Collectors.joining("\n"));   //склеить элементы символом \n
  }

  // очистка номера от посторонних символов пробел, - ( )
  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
