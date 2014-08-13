/**
 * 
 */
package com.gubs.basicJava;

import org.apache.log4j.Logger;

/**
 * @author gubs
 *
 */
public class StaticExample {

  private static final Logger log = Logger.getLogger(StaticExample.class);

  // static variable. s
  private static String name = "Gubendran aka gubs";

  // static block
  static {
    log.info("Static block executes first before main method when the class invoke..");
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    // Static method can be directly called from the className. It can also invoke through objectName
    StaticExample.testStaticMethod();

    // Static variable can be directly called from the className. It belongs to class and not to object. It
    // cannot invoke through object. Initialize once at the time of class loading
    log.info(StaticExample.name);

    StaticExample.InnerOrNestedClass stIn = new StaticExample.InnerOrNestedClass();
    stIn.testInnerMethod();

    StaticExample st = new StaticExample();
    st.test();

  }

  public static void testStaticMethod() {
    System.out
        .println("Static method can be called directly, without initiaing the object with class name. It can also invoke through objectName");
  }

  public void test() {
    log.info("Static variable can be accessed by instance java methods also " + name);
  }

  // Nested static class inside the class
  static class InnerOrNestedClass {
    public void testInnerMethod() {
      log.info("Testing Nested class");
    }
  }

}

