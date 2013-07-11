/**
 * 
 */
package com.gubs.springQuartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author gubs
 * 
 */
public class RunMeJob extends QuartzJobBean {

  private RunMeTask runMeTask;

  @Override
  protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
    this.runMeTask.printMe();
  }

  public RunMeTask getRunMeTask() {
    return runMeTask;
  }

  public void setRunMeTask(RunMeTask runMeTask) {
    this.runMeTask = runMeTask;
  }

}
