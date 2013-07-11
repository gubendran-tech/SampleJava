/**
 * 
 */
package com.gubs.springChannels;

import java.io.File;
import java.util.Map;

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
  @ServiceActivator
  public void onNewFileArrival(@Headers Map<String, Object> headers, @Payload File file) {

    System.out.printf("A new File Arried ", file.getAbsolutePath());

    System.out.println("The headers are ");
    for (String header : headers.keySet()) {
      System.out.println(String.format("%s=%s", headers.get(header)));
    }
  }
}
