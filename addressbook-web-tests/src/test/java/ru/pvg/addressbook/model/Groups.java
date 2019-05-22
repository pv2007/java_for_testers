package ru.pvg.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/*
   Created Владимир  at 17:46  16.05.2019
*/
public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;    //это атрибут, он никак не сязан с методом delegate()

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }


  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group) {
    Groups groups=new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupData group) {
    Groups groups=new Groups(this);
    groups.remove(group);
    return groups;
  }

}
