package com.gubs.webservices;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class SOAPBindingStub extends org.apache.axis.client.Stub implements SOAPClient {

  public String postDataUsingAxis(String xmlData, String url, String port, String userName, String password)
      throws RemoteException,
      ServiceException {

    org.apache.axis.description.OperationDesc oper = new org.apache.axis.description.OperationDesc();
    oper.setName("postdata");
    oper.addParameter(new javax.xml.namespace.QName("http://ar.masstech-pts.org/", "xmldata"),
        new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class,
        org.apache.axis.description.ParameterDesc.IN, false, false);
    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(java.lang.String.class);
    oper.setReturnQName(new javax.xml.namespace.QName("http://ar.masstech-pts.org/", "postdataResult"));
    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
    oper.setUse(org.apache.axis.constants.Use.LITERAL);

    Service service = new Service();

    org.apache.axis.client.Call _call = (Call) service.createCall();
    _call.setTargetEndpointAddress(url);
    _call.setPortName(new QName(port));
    _call.setOperation(oper);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("http://ar.masstech-pts.org/postdata");
    _call.setEncodingStyle(null);
    _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
    _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new javax.xml.namespace.QName("http://ar.masstech-pts.org/", "postdata"));

    setAuthHeaders(_call, userName, password);

    setRequestHeaders(_call);
    setAttachments(_call);
    java.lang.Object _resp = _call.invoke(new java.lang.Object[] { xmlData });

    if (_resp instanceof java.rmi.RemoteException) {
      throw (java.rmi.RemoteException) _resp;
    } else {
      extractAttachments(_call);
      try {
        return (java.lang.String) _resp;
      } catch (java.lang.Exception _exception) {
        return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
      }
    }

  }

  protected void setAuthHeaders(org.apache.axis.client.Call call, String userName, String password) {

    // This creates the header used for authentication.
    org.apache.axis.message.SOAPHeaderElement header = new org.apache.axis.message.SOAPHeaderElement(
        "http://ar.masstech-pts.org/", "cAuthentication");
    try {
      javax.xml.soap.SOAPElement UserName = header.addChildElement("UserName");
      javax.xml.soap.SOAPElement Password = header.addChildElement("Password");

      // This populates that header with the values
      UserName.addTextNode(userName);
      Password.addTextNode(password);

      // This adds the header to the envelope
      call.addHeader(header);
    } catch (Exception e) {
      // System.err.println("Caught Exception (" + e.getFaultCode() + "): " + e.getMessage());
      System.err.println("Caught Exception (" + e.toString() + ")");
      // System.exit(-1);
    }
  }

  private org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
    try {
      // org.apache.axis.client.Call _call = (org.apache.axis.client.Call) super.service.createCall();
      if (super.maintainSessionSet) {
        _call.setMaintainSession(super.maintainSession);
      }
      if (super.cachedUsername != null) {
        _call.setUsername(super.cachedUsername);
      }
      if (super.cachedPassword != null) {
        _call.setPassword(super.cachedPassword);
      }
      if (super.cachedEndpoint != null) {
        _call.setTargetEndpointAddress(super.cachedEndpoint);
      }
      if (super.cachedTimeout != null) {
        _call.setTimeout(super.cachedTimeout);
      }
      if (super.cachedPortName != null) {
        _call.setPortName(super.cachedPortName);
      }
      java.util.Enumeration keys = super.cachedProperties.keys();
      while (keys.hasMoreElements()) {
        java.lang.String key = (java.lang.String) keys.nextElement();
        _call.setProperty(key, super.cachedProperties.get(key));
      }
      return _call;
    } catch (java.lang.Throwable t) {
      throw new org.apache.axis.AxisFault("Failure trying to get the Call object", t);
    }
  }

}
