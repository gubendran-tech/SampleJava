package com.gubs.JMS;

import org.apache.log4j.Logger;

public class JmsMQConsumer {

  private static Logger logger = Logger.getLogger(JmsMQConsumer.class);

  public void handleMessage() {
    logger.debug("Coming....handleMessage");
  }

}
