/**
 * 
 */
package com.gubs.JMS;

/**
 * @author gubs
 * 
 */
public class TestJMSMQ {

  /**
   * @param args
   */
  public static void main(String[] args) {

    thread(new HelloWorldProducer(), false);
    thread(new HelloWorldProducer(), false);
  }

  private static void thread(Runnable helloWorldProducer, boolean b) {
    // TODO Auto-generated method stub

  }

  public static class HelloWorldProducer implements Runnable {

    @Override
    public void run() {

    }

  }

}
