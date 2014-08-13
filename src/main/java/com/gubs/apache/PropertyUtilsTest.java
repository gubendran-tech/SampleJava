/**
 * 
 */
package com.gubs.apache;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * @author gubs
 * 
 */
public class PropertyUtilsTest {

  private static final Logger log = Logger.getLogger(PropertyUtilsTest.class);

  /**
   * @param args
   */
  public static void main(String[] args) {

    PropertyUtilsTest pt = new PropertyUtilsTest();
    pt.testUtils();

  }

  private void testUtils() {
    Car car = new Car();
    try {
      PropertyUtils.setProperty(car, "owner", "gubs");
      String owner = (String) PropertyUtils.getProperty(car, "owner");
      log.info("Owner.." + owner);
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }

}


