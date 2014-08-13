/**
 * 
 */
package com.gubs.springQuartz;

import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author gubs
 * 
 */
public class SpringScheduleTask {

  private static final Logger log = Logger.getLogger(SpringScheduleTask.class);

  public void scheduleTask() {
    log.info("Spring Schedule Task, scheduleTask " + new Date(System.currentTimeMillis()).toString());
  }

}
