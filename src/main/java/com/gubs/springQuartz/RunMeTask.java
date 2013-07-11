package com.gubs.springQuartz;

import java.util.Date;

/**
 * 
 * @author gubs
 */

// This is the schedular class i want to schedule
public class RunMeTask {

  private String person;

  public RunMeTask(String person) {
    super();
    this.person = person;
  }

  public RunMeTask() {

  }

  public String getPerson() {
    return person;
  }

  public void setPerson(String person) {
    this.person = person;
  }

  public void printMe() {
    System.out.println("RunMe Task printMe method. Spring and Quartz : "
        + new Date(System.currentTimeMillis()).toString() + " by person " + this.person);
  }

}
