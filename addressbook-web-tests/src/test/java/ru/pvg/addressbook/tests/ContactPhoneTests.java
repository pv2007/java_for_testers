package ru.pvg.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.pvg.addressbook.model.ContactData;
import ru.pvg.addressbook.model.Contacts;

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

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));

  }
  // очистка номера от посторонних символов пробел, - ( )
  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
