package com.gubs.webservices;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;

public class SOAPClientUsingSAAJ {

  private static final Logger log = Logger.getLogger(SOAPClientUsingSAAJ.class);

    /**
   * Starting point for the SAAJ - SOAP Client Testing SAAJ -> SOAP with Attachments API for Java (SAAJ)
   * http://stackoverflow.com/questions/15940234/how-to-do-a-soap-web-service-call-from-java-class
   * 
   * http://www.intertech.com/Blog/java-soap-with-attachments-api-and-some-interesting-xml-tools/
   */
    public static void main(String args[]) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
      // String url = "http://ar.masstech-pts.org/testpostdata";
//      String url = "http://ar.masstech-pts.org/pts_ar.asmx";
      String url = "http://ar.masstech-pts.org/pts_ar.asmx";
      java.net.URL endPoint = new URL("http://ar.masstech-pts.org/pts_ar.asmx");

      SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), endPoint);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

    String serverURI = "http://ar.masstech-pts.org/";

    String bodyText = "<ARData><ptsSystem sysType=\"PV\"><sysID>NFG-NN1981-15408</sysID><MonthlyData><PVmonthlyData><kwhRegistry>45.00</kwhRegistry><regDateTime>2014-05-15T10:24:00</regDateTime><serviceCostsInDollars>10</serviceCostsInDollars></PVmonthlyData></MonthlyData></ptsSystem></ARData>";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();

    envelope.addAttribute(new QName("xmlns:xsi"), "http://www.w3.org/2001/XMLSchema-instance");
    envelope.addAttribute(new QName("xmlns:xsd"), "http://www.w3.org/2001/XMLSchema");
    /*
     * envelope.addNamespaceDeclaration("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
     * envelope.addNamespaceDeclaration("xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
     */
    // envelope.addNamespaceDeclaration("example", serverURI);

        /*
        Constructed SOAP Request Message:
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <example:VerifyEmail>
                    <example:email>mutantninja@gmail.com</example:email>
                    <example:LicenseKey>123</example:LicenseKey>
                </example:VerifyEmail>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
    SOAPElement soapBodyElem = soapBody.addChildElement("testpostdata");
    soapBodyElem.addAttribute(new QName("xmlns"), "http://ar.masstech-pts.org/");

    SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("xmldata");

    soapBodyElem1.addTextNode(bodyText);

    /*
     * SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("ARData");
     * 
     * soapBodyElem2.addAttribute(new QName("xmlns:xsi"), "http://www.w3.org/2001/XMLSchema-instance");
     * soapBodyElem2.addAttribute(new QName("xmlns"), "http://ar.masstech-pts/PTS-AR");
     * soapBodyElem2.addAttribute(new QName("xsi:schemaLocation"),
     * "http://ar.masstech-pts/PTS-AR http://ar.masstech-pts/PTS-AR");
     * 
     * soapBodyElem2.addChildElement("ptsSystem"); soapBodyElem2.addAttribute(new QName("sysType"), "PV");
     * 
     * soapBodyElem2.addChildElement("sysID"); soapBodyElem2.addTextNode("NFG-NN1981-15408");
     */

//    soapBodyElem1.addTextNode(bodyText);
    // soapBodyElem2.addTextNode(bodyText);

    MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
    mimeHeader.addHeader("SOAPAction", "http://ar.masstech-pts.org/testpostdata");

    // SOAPHeader
    SOAPHeader header = soapMessage.getSOAPHeader();

    QName headerName = new QName("http://ar.masstech-pts.org/", "cAuthentication");

    // SOAPHeaderElement headerElement = header.addHeaderElement(headerName);

    SOAPHeaderElement headerElement = header.addHeaderElement(headerName);


    String userName = "McKeon1111";
    String password = "Mkpw4196";
    SOAPElement soapUserName = soapBodyElem.addChildElement("UserName");
    soapUserName.addTextNode(userName);
    headerElement.addChildElement(soapUserName);

    // header.addChildElement(soapUserName);

    SOAPElement soapPassword = soapBodyElem.addChildElement("Password");
    soapPassword.addTextNode(password);
    headerElement.addChildElement(soapPassword);
    // header.addChildElement(soapPassword);

    // header.addHeader("SOAPAction", serverURI + "testpostdata");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
    System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

}