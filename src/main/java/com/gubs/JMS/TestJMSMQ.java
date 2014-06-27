/**
 * 
 */
package com.gubs.JMS;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsOperations;

/**
 * @author gubs
 * 
 */
public class TestJMSMQ {

  private JmsOperations smsJmsTemplate;

  public JmsOperations getSmsJmsTemplate() {
    return smsJmsTemplate;
  }

  public void setSmsJmsTemplate(JmsOperations smsJmsTemplate) {
    this.smsJmsTemplate = smsJmsTemplate;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    thread(new HelloWorldProducer(), false);
    thread(new HelloWorldProducer(), false);
  }

  private static void thread(Runnable helloWorldProducer, boolean b) {
    // TODO Auto-generated method stub

  }

  public static class HelloWorldProducer implements Runnable {

    @Override
    public void run() {

    }

  }

  public Integer getQueueCount(String destinationQueue) throws Exception {

    Integer count = 0;
    try {
      count = (Integer) getSmsJmsTemplate().browse(destinationQueue, new BrowserCallback<Object>() {

        @Override
        public Object doInJms(Session session, QueueBrowser queueBrowser) throws JMSException {
          int count = 0;
          Enumeration<?> msgs = queueBrowser.getEnumeration();

          while (msgs.hasMoreElements()) {
            count++;
            msgs.nextElement();
          }
          return new Integer(count);
        }
      });
    } catch (Exception e) {
      throw e;
    }
    return count;
  }

}
