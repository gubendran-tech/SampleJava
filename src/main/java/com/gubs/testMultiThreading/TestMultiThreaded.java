/**
 * 
 */
package com.gubs.testMultiThreading;

import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author gubs
 * 
 */
public class TestMultiThreaded {

  private static final Logger log = Logger.getLogger(TestMultiThreaded.class);

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
    log.info(threadSize);
  }

  private static void createChildThread(int id) {

    String threadName = "Gubs_" + id;
    if (!checkIfThreadAlreadyExist(threadName)) {
      Thread thread = new Thread(new ThreadTest(), threadName);
      thread.setDaemon(false);
      thread.start();
    } else {
      log.info("This thread already running.." + threadName);
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

  private static final Logger log = Logger.getLogger(ThreadTest.class);

  public void run() {
    log.info("Thread Name.." + Thread.currentThread().getName());

    if (Thread.currentThread().getName().equals("Gubs_3") || Thread.currentThread().getName().equals("Gubs_4")) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
