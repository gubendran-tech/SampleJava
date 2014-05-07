/**
 * 
 */
package com.gubs.basicJava;

/**
 * @author gubs
 *
 */
public class StaticExample {

  // static variable. s
  private static String name = "Gubendran aka gubs";

  // static block
  static {
    System.out.println("Static block executes first before main method when the class invoke..");
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    // Static method can be directly called from the className. It can also invoke through objectName
    StaticExample.testStaticMethod();

    // Static variable can be directly called from the className. It belongs to class and not to object. It
    // cannot invoke through object. Initialize once at the time of class loading
    System.out.println(StaticExample.name);

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
    System.out.println("Static variable can be accessed by instance java methods also " + name);
  }

  // Nested static class inside the class
  static class InnerOrNestedClass {
    public void testInnerMethod() {
      System.out.println("Testing Nested class");
    }
  }

}

