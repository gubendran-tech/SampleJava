package com.gubs.JAXB;

import java.io.File;
import java.io.Serializable;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLGenerator<T extends Serializable> {

  /**
   * This method will get the list of objects and construct the XML and provide the String XML Response
   * 
   * @param objects
   * @param filePath
   * @param fileName
   * @return
   */
  public String constructXMLFromObject(String filePath, String fileName, Class<?> T, T object) {

    File file = new File(filePath + fileName);
    StringWriter sw = new StringWriter();

    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(T);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // output preety printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
          "http://ar.masstech-pts.org/PTS-AR.xsd http://ar.masstech-pts.org/PTS-AR.xsd");
      // jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
      // "http://ar.masstech-pts/PTS-AR");

      jaxbMarshaller.marshal(object, file);
      jaxbMarshaller.marshal(object, sw);

    } catch (JAXBException e) {
      e.printStackTrace();
    }

    return sw.toString();
  }

  /**
   * This method will get the list of objects and construct the XML and provide the String XML Response
   * 
   * @param objects
   * @param filePath
   * @param fileName
   * @return
   */
  @SuppressWarnings("unchecked")
  public T provideObjectsFromXML(String filePath, String fileName, Class<?> T) {

    File file = new File(filePath + fileName);
    T element = null;
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(T);

      Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
      element = (T) jaxbUnMarshaller.unmarshal(file);

    } catch (JAXBException e) {
      e.printStackTrace();
    }

    return element;
  }

}
