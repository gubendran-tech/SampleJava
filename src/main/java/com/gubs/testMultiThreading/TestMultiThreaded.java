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
    for (int i = 0; i <= 5; i++) {
      createChildThread(i);
    }
    // This is to check if the threadAlreadyRunning so don't submit again.
    createChildThread(3);
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

    if (Thread.currentThread().getName().equals("Gubs_3")) {
      try {
        Thread.sleep(2000000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
