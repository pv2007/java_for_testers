package ru.pvg.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/*
   Created Владимир  at 20:01  20.05.2019
*/
public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;    //это атрибут, он никак не сязан с методом delegate()

  public Contacts (Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }


  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }


  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts=new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts=new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

}
