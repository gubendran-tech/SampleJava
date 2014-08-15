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
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * @author gubs
 * 
 *         HTTP Post and HTTP GET using URL
 * 
 */
public class HttpPostGetUsingURL {

  private static final Logger log = Logger.getLogger(HttpPostGetUsingURL.class);

  private static final String USER_AGENT = "Mozilla/5.0";

  /**
   * @param args
   */
  // http://172.26.103.51:8080/SMSService/rest/services/getData
  public static void main(String[] args) {

    // http://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/

    // postMethodUsingHttpUrlForDownloadImage();
    postMethodUsingHttpUrl();
    getMethodUsingHttpUrl();
  }

  private static void getMethodUsingHttpUrl() {
    String url = "http://www.google.com/search?q=mkyong";
    
    try {
      URL urlObj = new URL(url);
      HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
      conn.setDoOutput(true);

      // optional, default is GET
      conn.setRequestMethod("GET");

      // Add request header
      conn.setRequestProperty("User-Agent", USER_AGENT);

      int responseCode = conn.getResponseCode();
      log.info("Response Code.." + responseCode);

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        log.info("Response Content..." + currentLine);
      }
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

  private static void postMethodUsingHttpUrlForDownloadImage() {

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

  private static void postMethodUsingHttpUrl() {
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

      Map<String, List<String>> headerFields = conn.getHeaderFields();
      log.info("Header Fields..");
      for (Map.Entry<String, List<String>> headerField : headerFields.entrySet()) {
        log.info("Key.." + headerField.getKey() + "Values.." + headerField.getValue().toString());
      }
      
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
}
