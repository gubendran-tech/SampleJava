package com.gubs.springTutorial;

import java.util.Collection;
import java.util.Set;

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
public class SpringCollectionMainApp implements ApplicationContextAware {

  private static final Logger log = Logger.getLogger(SpringCollectionMainApp.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springCollections.xml");
    SpringCollection springCollection = (SpringCollection) applicationContext.getBean("springCollection");

    log.info("List from Spring.." + springCollection.getAddressList().toString());

    Collection<String> collection = springCollection.getAddressMap().values();

    log.info("Map from Spring collection Values.." + collection.toString());

    Set<String> setKey = springCollection.getAddressMap().keySet();
    log.info("Map from Spring collection Keys.." + setKey.toString());

    Set<String> springSet = springCollection.getAddressSet();
    log.info("Spring Set " + springSet.toString());

    // Access property elements
    String value = springCollection.getAddressProp().getProperty("one");
    log.info("Property Values.." + value);

  }

  @Override
  public void setApplicationContext(ApplicationContext arg0) throws BeansException {
    // TODO Auto-generated method stub

  }

}
