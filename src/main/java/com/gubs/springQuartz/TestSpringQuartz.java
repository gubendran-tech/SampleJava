package com.gubs.springQuartz;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author gubs
 * 
 */
public class TestSpringQuartz implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  /**
   * @param args
   */
  public static void main(String[] args) {
    new ClassPathXmlApplicationContext("spring-Quartz.xml");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
