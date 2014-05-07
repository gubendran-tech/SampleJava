/**
 * 
 */
package com.gubs.JAXB;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gubs http://stackoverflow.com/questions/17992435/how-to-get-all-namespaces-and-urls-to-populate-
 *         xsischemalocation-attribute-when
 * 
 */
public class JAXBTest {

  /**
   * @param args
   */
  public static void main(String[] args) {

    Photovoltaic ph = new Photovoltaic("NewJersey", 12.00,
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    XMLGenerator<Photovoltaic> xg = new XMLGenerator<Photovoltaic>();
    String xmlData = xg.constructXMLFromObject("/home/gubs/", "dummy.xml", Photovoltaic.class, ph);
    System.out.println("Xml data.." + xmlData);

  }

}
