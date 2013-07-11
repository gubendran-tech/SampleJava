package com.gubs.quartzSchedular;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class AlarmSchedule {

  public AlarmSchedule() {
    try {
      SchedulerFactory scdFactory = new StdSchedulerFactory();
      Scheduler schd = scdFactory.getScheduler();
      schd.start();
      JobDetail jd = new JobDetail("alarmJob", Scheduler.DEFAULT_GROUP, AlarmJob.class);

      JobDataMap jdm = new JobDataMap();
      jdm.put("auth_name", "Gubs");

      jd.setJobDataMap(jdm);

      Trigger triger = TriggerUtils.makeDailyTrigger("alarmTrigger", 15, 56);
      triger.setStartTime(new Date());

      // Map can be set in the Trigger as well
      JobDataMap jdmAtTrigger = new JobDataMap();
      jdmAtTrigger.put("trigger_name", "alarmTrigger");

      triger.setJobDataMap(jdmAtTrigger);

      schd.scheduleJob(jd, triger);

    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    new AlarmSchedule();

  }

}
