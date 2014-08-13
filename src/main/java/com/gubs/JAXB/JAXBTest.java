/**
 * 
 */
package com.gubs.JAXB;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author gubs http://stackoverflow.com/questions/17992435/how-to-get-all-namespaces-and-urls-to-populate-
 *         xsischemalocation-attribute-when
 * 
 */
public class JAXBTest {

  private static final Logger log = Logger.getLogger(JAXBTest.class);

  /**
   * @param args
   */
  public static void main(String[] args) {

    Photovoltaic ph = new Photovoltaic("NFG-NN1981-15408", 12.00,
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));

    XMLGenerator<Photovoltaic> xg = new XMLGenerator<Photovoltaic>();
    String xmlData = xg.constructXMLFromObject("/home/gubs/", "dummy.xml", Photovoltaic.class, ph);
    log.info("Xml data.." + xmlData);

  }

}
