package com.gubs.test.http;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestHttpPost {

  /**
   * @param args
   */
  public static void main(String[] args) {

    String url = "http://thaalamradio.com/voter.php?id=51";

    HttpClient client = new HttpClient();

    PostMethod postMethod = new PostMethod(url);
    postMethod.setRequestHeader("Content-type", "text/xml; charset=ISO-8859-1");

    for (int i = 0; i < 20; i++) {

      System.out.println("I " + i);
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
