/**
 * 
 */
package com.gubs.Objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author gubs
 * 
 */
public class TestObj {

  private static final Logger log = Logger.getLogger(TestObj.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    List<DummyObj> listObj = new ArrayList<DummyObj>();

    DummyObj doj = new DummyObj();

    for (int i = 1; i <= 2; i++) {
      doj = populateFields();
      doj.setChildName("Sai Theja.." + i);
      log.info("Sai Theja.." + i);

      listObj.add(doj);
    }

    log.info(listObj.toString());

  }

  private static DummyObj populateFields() {
    DummyObj doj = new DummyObj();
    doj.setId(1);
    doj.setParentName("Gubendran");
    doj.setDateCreated(new Date());

    return doj;
  }

}
