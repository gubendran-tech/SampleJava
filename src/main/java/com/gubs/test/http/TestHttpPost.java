package com.gubs.test.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import edu.emory.mathcs.backport.java.util.Arrays;

public class TestHttpPost {

  private static final Logger log = Logger.getLogger(TestHttpPost.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    // httpPostMethodForClick();
    try {
      httpPostMethodForRestJSON();
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void httpPostMethodForRestJSON() throws ParseException, IOException {
    DefaultHttpClient httpClient = new DefaultHttpClient();

    HttpPost httpPost = new HttpPost("http://172.26.103.51:8080/SMSService/rest/services/getData");

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
    } catch (ClientProtocolException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (response != null) {
      log.info("Response Header.." + Arrays.asList(response.getAllHeaders()).toString());
      log.info("Response String.." + EntityUtils.toString(response.getEntity()));
    }

    httpClient.getConnectionManager().shutdown();

  }

  private static void httpPostMethodForClick() {
    String url = "http://thaalamradio.com/voter.php?id=51";

    HttpClient client = new HttpClient();

    PostMethod postMethod = new PostMethod(url);
    postMethod.setRequestHeader("Content-type", "text/xml; charset=ISO-8859-1");

    for (int i = 0; i < 20; i++) {

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
