package com.gubs.testSuperCSV;

import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * 
 * @author gubs
 * 
 */
public class Customer {

  private String id;
  private String firstName;
  private String lastName;

  public final String[] headers = { "id", "firstName", "lastName" };

  public final String[] readHeaders = { "id", "firstName", "lastName" };

  public final CellProcessor[] readProcessors = new CellProcessor[] { null, // id
      null, // firstName
      null // LastName
  };

  /*
   * public final CellProcessor[] readProcessors = new CellProcessor[] { null, // id null, // firstName new
   * ConvertNullTo("") // LastName };
   */

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String[] getHeaders() {
    return headers;
  }

  public CellProcessor[] getReadProcessors() {
    return readProcessors;
  }

  public String[] getReadHeaders() {
    return readHeaders;
  }

}
