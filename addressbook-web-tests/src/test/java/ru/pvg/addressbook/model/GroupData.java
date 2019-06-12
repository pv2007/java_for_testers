package ru.pvg.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("group")     //задать имя тегу класса
@Entity
@Table(name="group_list")


public class GroupData {
  @XStreamOmitField         // пропустить поле при выводе в XML
  @Id
  @Column(name="group_id")
  private int id = Integer.MAX_VALUE;

  @Expose                   // выгружать поле при выводе в JSON
  @Column(name="group_name")
  private String groupName;

  @Expose                   // выгружать поле при выводе в JSON
  @Column(name="group_header")
  @Type(type = "text")
  private String groupHeader;

  @Expose                   // выгружать поле при выводе в JSON
  @Column(name="group_footer")
  @Type(type = "text")
  private String groupFooter;

  public int getId() {
    return id;
  }



  public String getGroupName() {
    return groupName;
  }

  public String getGroupHeader() {
    return groupHeader;
  }

  public String getGroupFooter() {
    return groupFooter;
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }


  public GroupData withName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public GroupData withHeader(String groupHeader) {
    this.groupHeader = groupHeader;
    return this;
  }

  public GroupData withFooter(String groupFooter) {
    this.groupFooter = groupFooter;
    return this;
  }


  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", groupName='" + groupName + '\'' +
            ", groupHeader='" + groupHeader + '\'' +
            ", groupFooter='" + groupFooter + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
    return result;
  }
}
