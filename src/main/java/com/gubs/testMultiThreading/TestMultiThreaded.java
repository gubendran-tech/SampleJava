/**
 * 
 */
package com.gubs.testMultiThreading;

import java.util.Set;

/**
 * @author gubs
 * 
 */
public class TestMultiThreaded {

  /**
   * @param args
   */
  public static void main(String[] args) {

    while (true) {
      // Check the threadCount
      checkThreadCount();

      for (int i = 0; i <= 5; i++) {
        createChildThread(i);
      }

      // This sleep is to kick the next thread again for the same
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  private static void checkThreadCount() {
    int threadSize = Thread.getAllStackTraces().size();
    System.out.println(threadSize);
  }

  private static void createChildThread(int id) {

    String threadName = "Gubs_" + id;
    if (!checkIfThreadAlreadyExist(threadName)) {
      Thread thread = new Thread(new ThreadTest(), threadName);
      thread.setDaemon(false);
      thread.start();
    } else {
      System.out.println("This thread already running.." + threadName);
    }

  }

  private static boolean checkIfThreadAlreadyExist(String threadName) {
    Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
    for (Thread thread : threadSet) {
      if (thread.getName().equals(threadName)) {
        return true;
      }
    }
    return false;
  }

}

class ThreadTest implements Runnable {
  public void run() {
    System.out.println("Thread Name.." + Thread.currentThread().getName());

    if (Thread.currentThread().getName().equals("Gubs_3") || Thread.currentThread().getName().equals("Gubs_4")) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
