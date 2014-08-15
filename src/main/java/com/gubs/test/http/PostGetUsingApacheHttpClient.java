package com.gubs.test.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class PostGetUsingApacheHttpClient {

  private static final Logger log = Logger.getLogger(PostGetUsingApacheHttpClient.class);
  private static final String USER_AGENT = "Mozilla/5.0";

  /**
   * @param args
   */
  public static void main(String[] args) {
    // http://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/

    // httpPostMethodForClick();
    try {
      httpPostExample();
      // httpGetExample();
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void httpGetExample() {
    DefaultHttpClient httpClient = new DefaultHttpClient();

    HttpGet httpGet = new HttpGet("http://www.google.com/search?q=developer");
    
    HttpResponse httpResponse = null;
    
    try {
      httpResponse = httpClient.execute(httpGet);
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      if (statusCode == 200) {
        // log.info("1st way to get Response content.." + EntityUtils.toString(httpResponse.getEntity()));

        BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String currentLine = null;
        while ((currentLine = br.readLine()) != null) {
          log.info("2nd way to get Response Content.." + currentLine);
        }
      }
    } catch (ClientProtocolException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    httpClient.getConnectionManager().shutdown();

  }

  private static void httpPostExample() throws ParseException, IOException {
    DefaultHttpClient httpClient = new DefaultHttpClient();

    HttpPost httpPost = new HttpPost("http://172.26.103.54:8080/SMSService/rest/services/getData");

    httpPost.setHeader("User-Agent", USER_AGENT);
    httpPost.setHeader("Content-Type", "application/json");
    httpPost.setHeader("Origin", "172.26.103.53");

    HttpResponse response = null;

    String requestString = "{\"requestType\":\"dynaMapManager\",\"DynaMapManagerRequestType\":\"GET_MAP_DEVICELIST_FOR_USER\",\"userId\":\"257\",\"custId\":24}";
    StringEntity se = null;
    try {
      se = new StringEntity(requestString);
    } catch (UnsupportedEncodingException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    httpPost.setEntity(se);


    try {
      response = httpClient.execute(httpPost);

      if (response.getStatusLine().getStatusCode() == 200) {
        log.info("httpPostExamples.." + EntityUtils.toString(response.getEntity()));

        for (org.apache.http.Header header : response.getAllHeaders()) {
          log.info("httpPostExample Headers..." + header.toString());
        }

      }
    } catch (ClientProtocolException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    httpClient.getConnectionManager().shutdown();

  }

  private static void httpPostMethodForClick() {
    String url = "http://thaalamradio.com/voter.php?id=51";

    HttpClient client = new HttpClient();

    PostMethod postMethod = new PostMethod(url);
    postMethod.setRequestHeader("Content-type", "text/xml; charset=ISO-8859-1");

    for (int i = 0; i < 1; i++) {

      log.info("I " + i);
      try {
        int statusCode = client.executeMethod(postMethod);
      } catch (HttpException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }

}
