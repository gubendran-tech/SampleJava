/**
 * 
 */
package com.gubs.springQuartz;

import java.util.Date;

/**
 * @author gubs
 * 
 */
public class SpringScheduleTask {

  public void scheduleTask() {
    System.out.println("Spring Schedule Task, scheduleTask " + new Date(System.currentTimeMillis()).toString());
  }

}
