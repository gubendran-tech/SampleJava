package com.gubs.basicJava;

import java.io.IOException;
import java.util.Properties;

public class parsePropertyFile {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    String env = System.getProperty("run.env");
    String propFileName = new StringBuilder("sms-corsfilter-").append(env).append(".properties").toString();

    Properties prop = new Properties();
    prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName));
  }

}
