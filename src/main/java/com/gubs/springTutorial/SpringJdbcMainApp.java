package com.gubs.springTutorial;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author gubs
 * 
 */
public class SpringJdbcMainApp implements ApplicationContextAware {

  private static final Logger log = Logger.getLogger(SpringJdbcMainApp.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springJdbc.xml");
    SpringJdbc springJdbc = (SpringJdbc) applicationContext.getBean("springJdbc");

    log.info("START::updateTelemetryCommandJobStatus...");
    
    springJdbc.updateTelemetryCommandJobStatus();
    
    log.info("END::updateTelemetryCommandJobStatus...");
   
  }

  @Override
  public void setApplicationContext(ApplicationContext arg0) throws BeansException {
    // TODO Auto-generated method stub

  }

}
