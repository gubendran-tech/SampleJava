/**
 * 
 */
package com.gubs.webservices;

import javax.xml.rpc.ServiceException;

/**
 * @author gubs
 *
 */
public interface SOAPClient extends java.rmi.Remote {
  public java.lang.String postDataUsingAxis(String xmlData, String url, String port, String userName, String password)
      throws java.rmi.RemoteException, ServiceException;
}

