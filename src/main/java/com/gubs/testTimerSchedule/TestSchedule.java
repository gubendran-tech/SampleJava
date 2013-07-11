package com.gubs.testTimerSchedule;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TestSchedule extends TimerTask {

  public static final int DELAY = 20 * 1000;

  @Override
  public void run() {
    System.out.println("Coming.." + Calendar.getInstance().toString());
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
