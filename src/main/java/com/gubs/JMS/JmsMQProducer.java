/**
 * 
 */
package com.gubs.JMS;

import java.io.Serializable;
import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

/**
 * @author gubs
 *
 */
public class JmsMQProducer<T extends Serializable> {

  private static Logger logger = Logger.getLogger(JmsMQProducer.class);

  private JmsOperations jmsQueueTemplate;
  private JmsOperations jmsTopicTemplate;

  public JmsOperations getJmsQueueTemplate() {
    return jmsQueueTemplate;
  }

  public void setJmsQueueTemplate(JmsOperations jmsQueueTemplate) {
    this.jmsQueueTemplate = jmsQueueTemplate;
  }

  public JmsOperations getJmsTopicTemplate() {
    return jmsTopicTemplate;
  }

  public void setJmsTopicTemplate(JmsOperations jmsTopicTemplate) {
    this.jmsTopicTemplate = jmsTopicTemplate;
  }

  public void sendMessageToQueue(String queueName, final String message) {
    MessageCreator messageCreator = new MessageCreator() {

      @Override
      public Message createMessage(Session session) throws JMSException {
        TextMessage textMessage = null;
        textMessage = session.createTextMessage();
        textMessage.setStringProperty("text", message);
        return textMessage;
      }
    };
    
    getJmsQueueTemplate().send(queueName, messageCreator);
  }

  public void sendMessageToTopic(String topicName, final String message) {
    MessageCreator messageCreator = new MessageCreator() {

      @Override
      public Message createMessage(Session session) throws JMSException {
        TextMessage textMessage = null;
        textMessage = session.createTextMessage();
        textMessage.setStringProperty("text", message);
        return textMessage;
      }
    };

    getJmsQueueTemplate().send(topicName, messageCreator);
  }

  public void receiveMessageFromQueue(String destinationQueue) {
    Message message = getJmsQueueTemplate().receive(destinationQueue);
    try {
      logger.info("Receive Message " + message.getStringProperty("text"));
    } catch (JMSException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public Integer getQueueCount(String destinationQueue) throws Exception {

    Integer count = 0;
    try {
      count = (Integer) getJmsQueueTemplate().browse(destinationQueue, new BrowserCallback<Object>() {

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
