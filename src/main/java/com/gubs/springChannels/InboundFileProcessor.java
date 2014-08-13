/**
 * 
 */
package com.gubs.springChannels;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * @author gubs
 * 
 *         Refer : http://blog.springsource.org/2010/08/23/secure-file-transfer-the-only-way-to-fly/
 */
@Component
public class InboundFileProcessor {

  private static final Logger log = Logger.getLogger(InboundFileProcessor.class);

  @ServiceActivator
  public void onNewFileArrival(@Headers Map<String, Object> headers, @Payload File file) {

    System.out.printf("A new File Arried ", file.getAbsolutePath());

    log.info("The headers are ");
    for (String header : headers.keySet()) {
      log.info(String.format("%s=%s", headers.get(header)));
    }
  }
}
