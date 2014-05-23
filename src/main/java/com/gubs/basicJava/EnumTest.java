/**
 * 
 */
package com.gubs.basicJava;

/**
 * @author gubs
 * 
 */
public enum EnumTest {

  CODE_OWNER("gubs", "Gubendran aka gubs works in Java"), HOME_OWNER("Sai Theja", "Son of Gubendran");

  private String name;
  private String description;

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
