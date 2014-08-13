/**
 * 
 */
package com.gubs.basicJava;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gubs
 * 
 */
public enum EnumTest {

  CODE_OWNER("gubs", "Gubendran aka gubs works in Java"), HOME_OWNER("Sai Theja", "Son of Gubendran"), OWNER("Kavitha",
      "she does everything");

  private String name;
  private String description;

  private static final Map<String, EnumTest> lookup = new HashMap<String, EnumTest>();
  // Static methods will be executed whenever the class is called or instantiated
  static {
    // EnumSet is faster
    for (EnumTest etest : EnumSet.allOf(EnumTest.class)) {
      lookup.put(etest.getDescription(), etest);
    }
  }

  // static method because people can call without instantiate the object
  public static EnumTest get(String description) {
    return lookup.get(description);
  }

  // Below is private constructor in the className for Enum calls
  private EnumTest(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }


}
