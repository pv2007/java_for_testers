package ru.pvg.addressbook.model;

/*
   Created Владимир  at 20:31  07.05.2019
*/
public class ContactData {
  private final String contactFirstName;
  private final String contactMiddleName;
  private final String contactLastName;
  private final String contactNickName;
  private final String contactTitle;
  private final String contactCompany;

  public ContactData(String contactFirstName, String contactMiddleName, String contactLastName, String contactNickName, String contactTitle, String contactCompany) {
    this.contactFirstName = contactFirstName;
    this.contactMiddleName = contactMiddleName;
    this.contactLastName = contactLastName;
    this.contactNickName = contactNickName;
    this.contactTitle = contactTitle;
    this.contactCompany = contactCompany;
  }

  public String getContactFirstName() {
    return contactFirstName;
  }

  public String getContactMiddleName() {
    return contactMiddleName;
  }

  public String getcontactLastName() {
    return contactLastName;
  }

  public String getContactNickName() {
    return contactNickName;
  }

  public String getContactTitle() {
    return contactTitle;
  }

  public String getContactCompany() {
    return contactCompany;
  }
}
