package ru.pvg.addressbook.model;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String lastName;
  private String title;
  private String middleName;
  private String nickName;
  private String company;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String fax;
  private String group;
  private String allPhones;

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

//  public ContactData(int id, String firstName, String middleName, String lastName, String nickName, String title, String company, String address, String homePhone, String mobilePhone, String workPhone, String fax, String group) {
//    this.id = id;
//    this.firstName = firstName;
//    this.middleName = middleName;
//    this.lastName = lastName;
//    this.nickName = nickName;
//    this.title = title;
//    this.company = company;
//    this.address = address;
//    this.homePhone = homePhone;
//    this.mobilePhone = mobilePhone;
//    this.workPhone = workPhone;
//    this.fax = fax;
//    this.group = group;
//  }
//
//  public ContactData(String firstName, String middleName, String lastName, String nickName, String title, String company, String address, String homePhone, String mobilePhone, String workPhone, String fax, String group) {
//    this.id = 0;
//    this.firstName = firstName;
//    this.middleName = middleName;
//    this.lastName = lastName;
//    this.nickName = nickName;
//    this.title = title;
//    this.company = company;
//    this.address = address;
//    this.homePhone = homePhone;
//    this.mobilePhone = mobilePhone;
//    this.workPhone = workPhone;
//    this.fax = fax;
//    this.group = group;
//  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getFax() {
    return fax;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

   @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }


  public ContactData withId(int index) {
    this.id = index;
    return this;
  }


  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
}
