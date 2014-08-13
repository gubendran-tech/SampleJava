package com.gubs.test.http;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * 
 * @author gubs
 * 
 */
public class RestfulJavaClientUsingURL {

  private static final Logger log = Logger.getLogger(RestfulJavaClientUsingURL.class);

  /**
   * @param args
   */
  // http://172.26.103.51:8080/SMSService/rest/services/getData
  public static void main(String[] args) {
    // RestPOSTMethod();
    // restGetMethod();
    restPOSTMethodIview();
    // restPostMethod();
    // restPostMethodToDownloadFile();

  }

  private static void restPostMethodToDownloadFile() {

    try {
      URL url = new URL("http://localhost:8080/SMSService/rest/services/downloadAttachment");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");

      String requestData = "{\"custId\":56, \"fileName\":\"7day.png\",\"requestType\":\"deviceManager\"}";
      OutputStream os = conn.getOutputStream();
      os.write(requestData.getBytes());
      os.flush();

      if (conn.getResponseCode() == 200) {
        InputStream stream = conn.getInputStream();

        OutputStream out = new FileOutputStream("/home/gubs/7day.png");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = stream.read(buffer)) != -1) {
          out.write(buffer, 0, bytesRead);
        }
        out.close();
      }
      conn.disconnect();

    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  private static void restPOSTMethodIview() {
    try {
      URL url = new URL("http://172.26.103.51:8080/SMSService/rest/services/getData");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");

      String requestData = "{\"requestType\":\"dynaMapManager\",\"DynaMapManagerRequestType\":\"GET_MAP_DEVICELIST_FOR_USER\",\"userId\":\"257\",\"custId\":24}";
      OutputStream os = conn.getOutputStream();
      os.write(requestData.getBytes());
      os.flush();
      os.close();

      /*
       * if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) { throw new
       * RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); }
       */

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String output;
      while ((output = br.readLine()) != null) {
        log.info(output);
      }
      br.close();
      conn.disconnect();

    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void restGetMethod() {
    try {
      URL url = new URL("http://localhost:8080/CXFRestfulTutorial/rest/getName");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-Type", "application/json");

      String requestData = "{\"requestType\":\"dynaMapManager\",\"DynaMapManagerRequestType\":\"GET_MAP_DEVICELIST_FOR_USER\",\"userId\":\"257\",\"custId\":24}";
      // OutputStream os = conn.getOutputStream();
      // os.write(requestData.getBytes());
      // os.flush();

      /*
       * if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) { throw new
       * RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); }
       */

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String output;
      while ((output = br.readLine()) != null) {
        log.info(output);
      }
      conn.disconnect();

    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void restPostMethod() {
    try {
      URL url = new URL("http://localhost:8080/CXFRestfulTutorial/rest/changeName");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");

      String requestData = "{\"Student\":{\"name\":\"gubs\"}}";
      OutputStream os = conn.getOutputStream();
      os.write(requestData.getBytes());
      os.flush();

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String output;
      while ((output = br.readLine()) != null) {
        log.info(output);
      }

    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
