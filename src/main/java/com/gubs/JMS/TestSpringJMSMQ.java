package com.gubs.JMS;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * http://www.javabeat.net/integrating-spring-with-jms/
 * 
 * @author gubs
 * 
 */
public class TestSpringJMSMQ implements ApplicationContextAware {

  private static Logger logger = Logger.getLogger(TestSpringJMSMQ.class);

  private ApplicationContext applicationContext;
  private String queueName;
  private String topicName;

  public static void main(String args[]) {
    logger.debug("Entering TestSpring JMSMQ main..");
    new ClassPathXmlApplicationContext("spring-jms.xml");
  }

  public void init() {
    JmsMQProducer jmsMQProducer = (JmsMQProducer) applicationContext.getBean("jmsMQProducer");

    // Send message to Queue
    jmsMQProducer.sendMessageToQueue(getQueueName(), "Gubs Testing Queue..");

    // Get message count to Queue
    Integer queueCount = null;
    try {
      queueCount = jmsMQProducer.getQueueCount(getQueueName());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logger.debug("init method.." + queueCount);

    // Receive message from Queue
    jmsMQProducer.receiveMessageFromQueue(getQueueName());

    // Send Message to Topic
    jmsMQProducer.sendMessageToTopic(getTopicName(), "Gubs Testing Topic..");

  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;

  }

  public String getQueueName() {
    return queueName;
  }

  public void setQueueName(String queueName) {
    this.queueName = queueName;
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

}
