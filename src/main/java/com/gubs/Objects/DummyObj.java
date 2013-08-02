package com.gubs.Objects;

import java.util.Date;

/**
 * 
 * @author gubs
 * 
 */
public class DummyObj {

  private int Id;
  private String parentName;
  private String childName;
  private Date dateCreated;

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public String getChildName() {
    return childName;
  }

  public void setChildName(String childName) {
    this.childName = childName;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  @Override
  public String toString() {
    return "DummyObj [Id=" + Id + ", parentName=" + parentName + ", childName=" + childName + ", dateCreated="
        + dateCreated + "]";
  }

}
