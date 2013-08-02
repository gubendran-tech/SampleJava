/**
 * 
 */
package com.gubs.Objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gubs
 * 
 */
public class TestObj {

  /**
   * @param args
   */
  public static void main(String[] args) {
    List<DummyObj> listObj = new ArrayList<DummyObj>();

    DummyObj doj = new DummyObj();

    for (int i = 1; i <= 2; i++) {
      doj = populateFields();
      doj.setChildName("Sai Theja.." + i);
      System.out.println("Sai Theja.." + i);

      listObj.add(doj);
    }

    System.out.println(listObj.toString());

  }

  private static DummyObj populateFields() {
    DummyObj doj = new DummyObj();
    doj.setId(1);
    doj.setParentName("Gubendran");
    doj.setDateCreated(new Date());

    return doj;
  }

}
