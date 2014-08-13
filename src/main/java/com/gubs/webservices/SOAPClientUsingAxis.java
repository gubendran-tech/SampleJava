package com.gubs.webservices;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.gubs.JAXB.Photovoltaic;
import com.gubs.JAXB.XMLGenerator;

/**
 * @author gubs
 *
 */
public class SOAPClientUsingAxis {

  private static final Logger log = Logger.getLogger(SOAPClientUsingAxis.class);

  /**
   * @param args
   */
  public static void main(String[] args) {

    Photovoltaic ph = new Photovoltaic("NFG-NN1981-15408", 12.00,
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));

    XMLGenerator<Photovoltaic> xg = new XMLGenerator<Photovoltaic>();
    String xmlData = xg.constructXMLFromObject("/home/gubs/", "dummy.xml", Photovoltaic.class, ph);
    log.info("XML Data.." + xmlData);

    String userName = "McKeon1111";
    String password = "Mkpw4196";
    java.lang.String soapURL = "http://ar.masstech-pts.org/pts_ar.asmx";
    String soapPort = "PTS_ARSoap";

    SOAPBindingStub soapBindingStub = new SOAPBindingStub();
    try {
      String response = soapBindingStub.postDataUsingAxis(xmlData, soapURL, soapPort, userName, password);
      log.info("Response .." + response);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // log.info("Response " + response);
    catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }



}
