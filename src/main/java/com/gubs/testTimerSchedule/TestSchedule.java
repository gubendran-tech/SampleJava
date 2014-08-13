package com.gubs.testTimerSchedule;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class TestSchedule extends TimerTask {

  private static final Logger log = Logger.getLogger(TestSchedule.class);

  public static final int DELAY = 20 * 1000;

  @Override
  public void run() {
    log.info("Coming.." + Calendar.getInstance().toString());
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    TimerTask testSchedule = new TestSchedule();

    Timer timer = new Timer();
    timer.schedule(testSchedule, DELAY, DELAY);
  }

}
