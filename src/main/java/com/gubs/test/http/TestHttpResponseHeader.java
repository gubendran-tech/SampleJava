package com.gubs.test.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestHttpResponseHeader {

  /**
   * @param args
   */
  public static void main(String[] args) {

    try {

      URL url = new URL("http://mkyong.com");
      URLConnection conn = url.openConnection();
      Map<String, List<String>> headerFields = conn.getHeaderFields();

      for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
        System.out.println("Key header fields name " + entry.getKey() + " field values " + entry.getValue());
      }

      List<String> servers = headerFields.get("Server");
      if (servers != null) {
        Iterator<String> itr = servers.iterator();
        while (itr.hasNext()) {
          System.out.println("Iterator servers : " + itr.next());
        }
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
