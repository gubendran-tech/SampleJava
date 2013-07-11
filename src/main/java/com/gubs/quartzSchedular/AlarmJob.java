/**
 * 
 */
package com.gubs.quartzSchedular;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author gubs
 * 
 */
public class AlarmJob implements Job {

  @Override
  public void execute(JobExecutionContext jobContext) throws JobExecutionException {

    // jobContext.getMergedJobDataMap() is to get both the JDM object data and trigger JDM data

    JobDataMap jdMap = jobContext.getMergedJobDataMap();

    // get the data from JobDetail jobContext.getJobDetail().getJobDataMap();

    // get the data from trigger JDM jobContext.getTrigger().getJobDataMap();

    System.out.println("Job Data Map.." + jdMap.getString("auth_name"));
    System.out.println("Trigger Name.." + jdMap.getString("trigger_name"));
  }
}
