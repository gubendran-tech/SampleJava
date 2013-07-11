package com.gubs.springQuartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

public class QuartzJobCreation {

  private Scheduler scheduleFactory;

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      new QuartzJobCreation().execute();
    } catch (Exception e) {
      System.out.println("Exception " + e.getMessage());
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void execute() throws Exception {
    String person = "Gubendran lakshmanan, Kavitha and Sai Theja";
    String groupName = "Gubs_Grp";
    String jobName = "Gubs Sai ";

    MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

    RunMeTask runMeTask = new RunMeTask(person);

    jobDetail.setTargetObject(runMeTask);
    jobDetail.setTargetMethod("printMe");
    jobDetail.setGroup(groupName);
    jobDetail.setName(jobName);

    jobDetail.setConcurrent(false);
    jobDetail.afterPropertiesSet();

    CronTriggerBean cbn = new CronTriggerBean();
    cbn.setCronExpression("8 0,5,10,15,20,25,30,35,40,45,50,55 * * * ?");
    cbn.setGroup(groupName);
    cbn.setName(jobName);

    cbn.setJobDetail((JobDetail) jobDetail.getObject());
    cbn.afterPropertiesSet();

    if (getScheduleFactory().getJobDetail(groupName, jobName) == null) {
      getScheduleFactory().scheduleJob((JobDetail) jobDetail.getObject(), cbn);

    }
  }

  public Scheduler getScheduleFactory() {
    return scheduleFactory;
  }

  public void setScheduleFactory(Scheduler scheduleFactory) {
    this.scheduleFactory = scheduleFactory;
  }

}
