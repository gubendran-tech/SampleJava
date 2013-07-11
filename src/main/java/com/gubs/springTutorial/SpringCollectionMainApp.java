package com.gubs.springTutorial;

import java.util.Collection;
import java.util.Set;

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

  /**
   * @param args
   */
  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springCollectionsJdbc.xml");
    SpringCollection springCollection = (SpringCollection) applicationContext.getBean("springCollection");

    springCollection.updateTelemetryCommandJobStatus();

    System.out.println("List from Spring.." + springCollection.getAddressList().toString());

    Collection<String> collection = springCollection.getAddressMap().values();

    System.out.println("Map from Spring collection Values.." + collection.toString());

    Set<String> setKey = springCollection.getAddressMap().keySet();
    System.out.println("Map from Spring collection Keys.." + setKey.toString());

    Set<String> springSet = springCollection.getAddressSet();
    System.out.println("Spring Set " + springSet.toString());

    // Access property elements
    String value = springCollection.getAddressProp().getProperty("one");
    System.out.println("Property Values.." + value);

  }

  @Override
  public void setApplicationContext(ApplicationContext arg0) throws BeansException {
    // TODO Auto-generated method stub

  }

}
