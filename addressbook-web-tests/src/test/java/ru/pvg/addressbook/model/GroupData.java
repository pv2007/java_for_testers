package ru.pvg.addressbook.model;

public class GroupData {
  private final String groupName;
  private final String groupHeader;
  private final String groupFooter;

  @Override
  public String toString() {
    return "GroupData{" +
            "groupName='" + groupName + '\'' +
            ", groupHeader='" + groupHeader + '\'' +
            ", groupFooter='" + groupFooter + '\'' +
            '}';
  }

  public GroupData(String groupName, String groupHeader, String groupFooter) {
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupFooter = groupFooter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (groupName != null ? !groupName.equals(groupData.groupName) : groupData.groupName != null) return false;
    if (groupHeader != null ? !groupHeader.equals(groupData.groupHeader) : groupData.groupHeader != null) return false;
    return groupFooter != null ? groupFooter.equals(groupData.groupFooter) : groupData.groupFooter == null;

  }

  @Override
  public int hashCode() {
    int result = groupName != null ? groupName.hashCode() : 0;
    result = 31 * result + (groupHeader != null ? groupHeader.hashCode() : 0);
    result = 31 * result + (groupFooter != null ? groupFooter.hashCode() : 0);
    return result;
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
}
